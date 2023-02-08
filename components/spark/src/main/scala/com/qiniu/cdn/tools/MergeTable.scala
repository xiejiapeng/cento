package com.qiniu.cdn.tools

import org.apache.commons.cli._
import org.apache.commons.lang.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.log4j.Logger
import org.apache.spark.sql.catalyst.TableIdentifier
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.UUID


/**
 * 将某个表的指定分区内的文件压缩为一个文件
 *  功能
 *    1. 支持外部表和内部表
 *    2. 支持定义压缩比例 todo
 *    3. 支持压缩多个分区
 *    4. 处理stream table的fileIndex的问题 -> spark.sql.streaming.fileStreamSink.ignoreMetadata=false
 *    5. 怎么通知其他地方对表进行refresh呢？
 */
object MergeTable {

  val logger = Logger.getLogger(this.getClass)

  def parseArgs(args: Array[String]):scala.Option[CommandLine] = {
    val options = new Options

    val dbOption = new Option("d", "database", true, "database name. e.g. module")
    dbOption.setRequired(false)
    options.addOption(dbOption)

    val tableOption = new Option("t", "tableName", true, "table name. e.g. post")
    tableOption.setRequired(true)
    options.addOption(tableOption)

    val partitionsOption = new Option("p", "partitions", true, "partitions, e.g. a=1,b=2")
    partitionsOption.setRequired(true)
    options.addOption(partitionsOption)

    val compressOption = new Option("r","ratio",true,"target compress ratio, default 1")
    compressOption.setRequired(false)
    options.addOption(compressOption)

    val validateOption = new Option("v","validate",false,"validate record numbers before merging")
    validateOption.setRequired(false)
    options.addOption(validateOption)

    try {
      val command = new BasicParser().parse(options, args)
      Some(command)
    } catch {
      case _: Exception => {
        val helpFormatter = new HelpFormatter
        helpFormatter.printHelp("merge", options)
        None
      }
    }

  }

  def main(args:Array[String]): Unit = {
    implicit val spark = SparkSession.builder().appName("merge-table").enableHiveSupport().getOrCreate()
    val uuid = UUID.randomUUID().toString

    parseArgs(args).map(command => {
      val db = command.getOptionValue("database",spark.sessionState.catalog.getCurrentDatabase)
      val partitionStr = command.getOptionValue("partitions", "")
      val validate = command.hasOption("validate")

      val table = new TableIdentifier(command.getOptionValue("tableName"), Some(db))
      val catalogTable = spark.sessionState.catalog.getTableMetadata(table)
      val mergeTask = new MergeTask(uuid, catalogTable, partitionStr, 1, validate)

      logger.info(s"merge task details $mergeTask")

      if(!validateMerge(mergeTask)){
        logger.warn("not supported, exit")
        return
      }

      setUpForTask(mergeTask)

      var retry = false
      var maxRetryTime = 5

      do {
        //step 0. sync partitions first if table is external partition table
        if(mergeTask.isExternalTable && mergeTask.isPartitionTable){
          spark.sql(s"msck repair table ${table.table} sync partitions")
        }

        //step 1. read all data as a dataframe
        val rawData = prepareDataForTask(mergeTask)

        //step 2. write and coalesce into a new file and create a view for it
        val materialStatistic = materialForTask(mergeTask, rawData, 1)


        if(materialStatistic.success){
          val sql = mergeTask.replaceSql
          logger.info(s"replace sql:  $sql")
          spark.sql(sql)
          retry = false
          logger.info(s"successful, material and replace result $materialStatistic")
        } else {
          retry = true

        }

        maxRetryTime = maxRetryTime - 1
      } while(retry && maxRetryTime > 0)

      if(retry) {
        logger.info("fail to merge because of race condition")
      }

      //step 4. refresh table
      spark.sessionState.catalog.invalidateCachedTable(table)
      spark.sessionState.catalog.refreshTable(table)

      logger.info("all done")
    })

  }

  def validateMerge(mergeTask: MergeTask) : Boolean = {
    //如果是非分区表，则不可以指定分区
    if(!mergeTask.isPartitionTable && mergeTask.hasPartition) {
      logger.warn(s"not partition table ${mergeTask.tableName}, not support partitions")
      return false
    }

    //如果是分区表，一定要指定分区，可以指定多个分区
    if(mergeTask.isPartitionTable && !mergeTask.hasPartition) {
      logger.warn(s"please specify partition(s) for table ${mergeTask.tableName}")
      return false
    }

    //必须指定全部的分区
    val partitionNum = mergeTask.table.partitionColumnNames.length
    for(p <- mergeTask.partitionSpecs){
      if(p.keySet.size != partitionNum){
        logger.warn(s"you should specify all partition columns, which contains $partitionNum columns, but $p only contain ${p.keySet.size} columns.")
        return false
      }
    }


    true

  }

  def setUpForTask(mergeTask: MergeTask)(implicit spark: SparkSession): Unit = {
    /*
      如果每个分区执行一次sql，则没法保证原子性
      通过动态分区覆盖，一次性分区覆盖合并
     */
    if(mergeTask.dynamicPartition) {
      spark.conf.set("spark.sql.sources.partitionOverwriteMode", "dynamic")
    }

    mergeTask.currentDB.map(db => spark.sessionState.catalog.setCurrentDatabase(db))
    logger.info(s"now database switch to ${spark.sessionState.catalog.getCurrentDatabase}")
  }

  def prepareDataForTask(mergeTask: MergeTask)(implicit spark: SparkSession) : DataFrame = {
    var dataset = spark.table(mergeTask.tableName)
    val condition = mergeTask.filterExpr
    logger.info(s"raw data filter $condition")
    import org.apache.spark.sql.Column
    dataset = dataset.filter(new Column(condition))
    dataset.cache()
    dataset
  }

  /*
    path/of/data/a=1/b=2
    => path/of/data/.merge/a=1/b=2
    => path/of/data/a=1/b=2
   */
  def materialForTask(mergeTask: MergeTask, df: DataFrame, par: Int)(implicit spark: SparkSession): MaterialStatistic ={
    val origin = df.count()

    val path = mergeTask.tempPath
    val writer = df.coalesce(par).write
      .mode("overwrite")

    writer.parquet(path)
    logger.info(s"new records are materialized into $path")

    val view = mergeTask.view
    var replace = spark.read.parquet(path)

    //if use dynamic partition overwrite, don't drop partition column, otherwise, drop static partitions.
    if(!mergeTask.dynamicPartition){
      mergeTask.partitionSpecs(0).keys.map(p => replace = replace.drop(p))
    }

    replace.cache()
    replace.createOrReplaceTempView(view)

    val after = replace.count()

    new MaterialStatistic(path, view, origin, after)
  }

  def clearStatus(mergeTask: MergeTask): Unit = {
    if(!StringUtils.isEmpty(mergeTask.tempPath)){
      val fs = FileSystem.get(new Configuration())
      fs.delete(new Path(mergeTask.tempPath), true)
      logger.info(s"delete temp file ${mergeTask.tempPath}")
    } else {
      logger.info("wrong path !!")
    }
  }

  class MaterialStatistic(val path : String, val view : String, val expected: Long, val actual: Long) {
    def success = expected == actual


    override def toString = s"MaterialStatistic($path, $view, $expected, $actual, $success)"
  }

}
