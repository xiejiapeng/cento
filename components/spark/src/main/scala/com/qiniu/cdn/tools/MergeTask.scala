package main.scala.com.qiniu.cdn.tools

import com.qiniu.cdn.tools.MergeTable.logger
import org.apache.commons.lang.StringUtils
import org.apache.hadoop.fs.Path
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.catalog.CatalogTable
import org.apache.spark.sql.catalyst.catalog.CatalogTableType.EXTERNAL
import org.apache.spark.sql.catalyst.expressions.{And, EqualTo, Expression, Or}

class MergeTask(val id: String
                , val table:CatalogTable
                , val partitions: String
                , val compress: Double
                , val validate: Boolean)(implicit spark:SparkSession){

  val tableName = table.identifier.table

  val partitionSpecs = toMap(partitions)

  val dynamicPartition = partitionSpecs.length > 1

  val currentDB = table.identifier.database

  val isExternalTable = table.tableType == EXTERNAL

  val isPartitionTable = table.partitionColumnNames.length > 0

  val hasPartition = partitionSpecs.length > 0

  def filterExpr() : Expression = {
    partitionSpecs.map(m => m.map{case (k,v) => s"$k=$v"}.mkString(" and "))
      .map(exp => spark.sessionState.sqlParser.parseExpression(exp))
      .reduce((e1,e2) => Or(e1,e2))
  }


  val storage = table.storage.locationUri.get

  val tempPath = new Path(storage.getPath,".merging" + "-" + id).toString

  val view = s"merging-${id}".replace("-","")

  val replaceSql = partitionSpecs match {
    case _ if partitionSpecs.length == 1 => partitionSql()
    case _ => dynamicPartitionSql()
  }



  def toMap(a:String) :Seq[Map[String,String]] = {
    if(StringUtils.isEmpty(partitions))return Seq()

    val maps = a.split("\\|").map(s => s.split(",").map(ps => (ps.split("=")(0), ps.split("=")(1)))
      .toMap).toSeq

    logger.info(s"partition map is $maps")
    maps
  }

  def partitionSql() : String = {
    s"""
       |INSERT OVERWRITE TABLE $tableName
       |PARTITION ($partitions)
       |SELECT
       |*
       |FROM $view
       |""".stripMargin
  }

  def dynamicPartitionSql() : String = {
    s"""
       |INSERT OVERWRITE TABLE $tableName
       |SELECT
       |*
       |FROM $view
       |""".stripMargin
  }


  override def toString = s"MergeTask($tableName, $partitionSpecs, $dynamicPartition, $currentDB, $isExternalTable, $isPartitionTable, $hasPartition, $filterExpr, $storage, $tempPath, $view, $replaceSql, $id, $table, $partitions, $compress, $validate, $partitionSql, $dynamicPartitionSql)"
}
