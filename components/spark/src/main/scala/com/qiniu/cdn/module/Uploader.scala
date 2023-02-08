package com.qiniu.cdn.module

import com.qiniu.cdn.Functions
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger
import org.apache.spark.sql.types.{StringType, StructField, StructType, TimestampType}

/*
{"analyzeFlux":9489347.0,"analyzeLines":292,"createTime":1661843139874,
"domain":"vendmach.photo.silai8.com","duration":976,"etl":false,"fail":false,
"finishTime":1661843140850,"granularity":"1hour","host":"jjh1811",
"id":"db17ee6f-5bb1-493d-aaf4-bec2879be756","logTime":1661824800000,
"offlineCdn":"","onlineCdn":"qiniucdn","readFlux":120000823,"readLines":3410,
"storeFlux":120000823,"storeLines":3410,"writeFlux":120000823,"writeLines":3410}
 */

object Uploader {
  def main(args: Array[String]): Unit ={
    val spark = SparkSession.builder().appName("module-uploader").enableHiveSupport().getOrCreate()

    import org.apache.spark.sql.functions._
    import spark.implicits._

    val source = spark.readStream
      .format("kafka")
      .options(Map(("kafka.bootstrap.servers","jjh645:19092"),("subscribe","log-collector-logsys-uploader")))
      .load()

    val schemaStr =
      """
        | analyzeFlux double,
        | analyzeLines long,
        | createTime long,
        | domain string,
        | duration double,
        | etl boolean,
        | fail boolean,
        | finishTime long,
        | granularity string,
        | host string,
        | id string,
        | logTime long,
        | offlineCdn string,
        | onlineCdn string,
        | readFlux double,
        | readLines long,
        | storeFlux double,
        | storeLines long,
        | writeFlux double,
        | writeLines long
        |""".stripMargin

    val schema = StructType.fromDDL(schemaStr)

    val data = source
      .select(col("value").cast(StringType) as "value")
      .select(from_json(col("value"), schema) as "value")
      .select("value.*")

    import org.apache.spark.sql.functions._

    val du = udf(Functions.day)

    val time = data
      .withColumn("createTime", $"createTime" / 1000 cast(TimestampType) as "createTime")
      .withColumn("finishTime", $"finishTime" / 1000 cast(TimestampType) as "finishTime")
      .withColumn("logTime", $"logTime" / 1000 cast(TimestampType) as "logTime")
      .withColumn("day",du(col("logTime")))



    val createTable =
      """
        |create table if not exists module.uploader(
        | analyzeFlux double,
        | analyzeLines long,
        | createTime timestamp,
        | domain string,
        | duration double,
        | etl boolean,
        | fail boolean,
        | finishTime timestamp,
        | granularity string,
        | host string,
        | id string,
        | logTime timestamp,
        | offlineCdn string,
        | onlineCdn string,
        | readFlux double,
        | readLines long,
        | storeFlux double,
        | storeLines long,
        | writeFlux double,
        | writeLines long,
        | day int
        |)
        |using parquet
        |partitioned by (day)
        |clustered by (domain) into 3 buckets
        |""".stripMargin

    spark.sql(createTable)

    val query = time.writeStream
      .format("parquet")
      .partitionBy("day")
      .option("checkpointLocation","/tmp/implement/ck/uploader")
      .trigger(ProcessingTimeTrigger(10000))
      .toTable("module.uploader")

    spark.streams.awaitAnyTermination()

  }
}
