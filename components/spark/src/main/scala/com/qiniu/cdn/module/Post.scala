package com.qiniu.cdn.module

import com.qiniu.cdn.Functions
import org.apache.spark.sql.catalyst.expressions.Literal
import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger
import org.apache.spark.sql.streaming.OutputMode

/*
{"cdn":"aliyun","domain":"file.yallaludo.com"
,"flux":16542951,"logTime":"2022-08-30 16:10:00"
,"moduleName":"post","region":"oc","uid":1381562298}
 */

object Post {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("module-post").enableHiveSupport().getOrCreate()
    Functions.register(spark)

    val source = spark.readStream
      .format("kafka")
      .options(Map(("kafka.bootstrap.servers","jjh645:19092"),("assign","{\"module-traffic\":[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17]}")))
      .load()

    source.createOrReplaceTempView("source")

    spark.sql(
      """
        |create table if not exists module.post(
        | cdn string,
        | domain string,
        | flux long,
        | logTime string,
        | moduleName string,
        | region string,
        | uid long,
        | day string,
        | hour int
        |)
        |using parquet
        |partitioned by (day,hour)
        |clustered by (domain) into 3 buckets
        |""".stripMargin)


    spark.sql(
      """
        | create temporary view format as
        | select value.* from
        |   (select
        |     from_json(cast(value as string), 'cdn string, domain string, flux long, logTime string, moduleName string, region string, uid long') as value
        |   from source)
        |""".stripMargin)
    var time = spark.sql("select cdn, domain, flux, cast(strToTime(logTime,'yyyy-MM-dd HH:mm:ss') as timestamp) as logTime, moduleName, region, uid from format")
    time.printSchema()
    time = time.withWatermark("logTime","5 minutes")
    time.createOrReplaceTempView("time")

    val data = spark.sql(
      """
        |select
        | cdn,
        | domain,
        | sum(flux) as flux,
        | window(logTime, '5 minutes') as window,
        | region,
        | uid,
        | dayC(window(logTime, '5 minutes').start) as day,
        | hourC(window(logTime, '5 minutes').start) as hour
        |from time
        | group by cdn, domain, window(logTime, '5 minutes'), region, uid
        |""".stripMargin)



    val query = data
      .withColumn("logTime",new Column(spark.sessionState.sqlParser.parseExpression("timeToStr(window.start)")))
      .withColumn("moduleName",new Column(Literal("post")))
      .drop("window")
      .writeStream
      .format("parquet")
      .trigger(ProcessingTimeTrigger(10000))
      .option("checkpointLocation","/tmp/implement/ck/post")
      .outputMode(OutputMode.Append())
      .partitionBy("day","hour")
      .start("/tmp/implement/module/post")


    query.awaitTermination()



  }
}
