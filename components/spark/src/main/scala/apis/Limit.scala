package apis

import apis.Sort.sql
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.internal.SQLConf

object Limit {
  def main(args: Array[String]) : Unit = {
    sql
  }
  def sql() : Unit = {
    val spark = SparkSession.builder()
      .config(SQLConf.ADAPTIVE_EXECUTION_ENABLED.key,"false")
//      .config("spark.sql.sources.commitProtocolClass","org.apache.spark.internal.io.cloud.PathOutputCommitProtocol")
//      .config("spark.sql.parquet.output.committer.class","org.apache.spark.internal.io.cloud.BindingParquetOutputCommitter")
//      .config("spark.hadoop.fs.s3a.committer.magic.enabled","true")
//      .config("spark.hadoop.fs.s3a.bucket.all.committer.magic.enabled","true")
//      .config("spark.hadoop.fs.s3a.committer.name","magic")
//      .config("spark.hadoop.mapreduce.outputcommitter.factory.scheme.s3a","org.apache.hadoop.fs.s3a.commit.S3ACommitterFactory")
//      .config("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version","2")
//      .config("spark.hadoop.parquet.enable.summary-metadata","false")
//      .config("spark.sql.parquet.mergeSchema","false")
//      .config("spark.sql.parquet.filterPushdown","true")
      .config("spark.sql.sources.partitionOverwriteMode", "dynamic")
      .master("local[*]")
      .getOrCreate()

    val data = Seq((1, "Alice", 10), (3, "Cathy", 30), (2, "Bob", 20), (4, "David", 40))

    """
      |spark.sql.sources.commitProtocolClass org.apache.spark.internal.io.cloud.PathOutputCommitProtocol
      |spark.sql.parquet.output.committer.class org.apache.spark.internal.io.cloud.BindingParquetOutputCommitter
      |spark.hadoop.fs.s3a.committer.magic.enabled true
      |spark.hadoop.fs.s3a.bucket.all.committer.magic.enabled true
      |spark.hadoop.fs.s3a.committer.name magic
      |spark.hadoop.mapreduce.outputcommitter.factory.scheme.s3a org.apache.hadoop.fs.s3a.commit.S3ACommitterFactory
      |spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version 2
      |spark.hadoop.parquet.enable.summary-metadata false
      |spark.sql.parquet.mergeSchema false
      |spark.sql.parquet.filterPushdown true
      |""".stripMargin
    val df = spark.createDataFrame(data).toDF("id", "name", "score")
    df.write
      .partitionBy("name")
      .mode(SaveMode.Overwrite)
      .parquet("/tmp/students")
//    val df = spark.read.parquet("/Users/jixie/ffc7f5b3-5bce-4ab4-b14e-9c374b52955f-0_128-2-965_00000000000002.parquet")
//    df.createOrReplaceTempView("students")
//    val x = spark.sql("select * from students order by _hoodie_commit_time limit 2")
//    println("=== logic ===")
//    println(x.queryExecution.logical)
//    println("=== analyzed ===")
//    println(x.queryExecution.analyzed)
//    println("=== optimized ===")
//    println(x.queryExecution.optimizedPlan)
//    println("=== spark ===")
//    println(x.queryExecution.sparkPlan)
//    println("=== executed ===")
//    println(x.queryExecution.executedPlan)

//    Utils.optimized(spark, spark.sessionState.optimizer, x)
  }
}
