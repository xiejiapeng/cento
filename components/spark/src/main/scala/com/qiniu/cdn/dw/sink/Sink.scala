package main.scala.com.qiniu.cdn.dw.sink

import com.qiniu.cdn.{Commons, Functions}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger
import org.apache.spark.sql.streaming.OutputMode

object Sink {
  def main(args:Array[String]) = {
    val spark = SparkSession.builder().appName("douyin-sink").enableHiveSupport().getOrCreate()
    Functions.register(spark)

    val createTableSql =
      """
        |create table if not exists module.dsink(
        | domain string,
        | cdn_provider string,
        | request_time timestamp,
        | bytes_sent long,
        | hitmiss string,
        | status_code string,
        | day string,
        | hour int,
        | minute int
        |)
        |using parquet
        |partitioned by (day, hour, minute)
        |clustered by (domain) into 3 buckets
        |location '/tmp/implement/file'
        |""".stripMargin

    spark.sql(createTableSql)


    println("show tables")
    spark.sql("show tables").show(200,false)

    Commons.registerView(spark,"cdnlog-raw-0", None)

    val formatSql =
      """
        |select
        | domain
        | , cdn_provider
        | , request_time
        | , bytes_sent
        | , hitmiss
        | , status_code
        | , dayC(request_time) as day
        | , hourC(request_time) as hour
        | , five_minuteC(request_time) as minute
        |from format
        |""".stripMargin

    spark.sql(formatSql)
      .writeStream
      .format("parquet")
      .outputMode(OutputMode.Append())
//      .partitionBy("day","hour","minute")
      .option("checkpointLocation","/tmp/implement/ck/module-sink")
      .option("maxOffsetsPerTrigger","1000000")
      .trigger(ProcessingTimeTrigger(10000))
      .toTable("module.dsink")

    spark.sessionState.streamingQueryManager.awaitAnyTermination()
  }
}
