package com.qiniu.cdn

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, current_timestamp, from_json, from_unixtime}
import org.apache.spark.sql.types.{DataType, StringType, StructType}

object Commons {
  val rtlogSchema =
    """
      |  status_code STRING,
      |  server_ip STRING,
      |  hitmiss STRING,
      |  domain STRING,
      |  cdn_provider STRING,
      |  bytes_sent LONG,
      |  request_body_length LONG,
      |  request_length LONG,
      |  request_time LONG,
      |  client_ip STRING
      |
      |""".stripMargin

  val topic_edge_traffic = "impl_edge_traffic"
  val topic_edge_code = "impl_edge_code"
  val topic_edge_hit = "impl_edge_hit"
  val topic_pre_cal = "impl_pre_cal"
  val filteredDomain = "v18.toutiaovod.com"
  val startDay = "20220817"
  val endDay = "20220818"

  def registerView(spark:SparkSession, topic: String, waterMark: Option[String], c:String = "request_time"): Unit ={
    val kafka = spark
      .readStream
      .format("kafka")
      .options(Map(("kafka.bootstrap.servers","jjh645:19092"),("subscribe",topic)))
      .load()

    val jsonSchema = StructType.fromDDL(Commons.rtlogSchema)
    var format = kafka.select(from_json(col("value").cast(StringType),jsonSchema) as "value")
      .select("value.*")
      .withColumn("region",Functions.ipToRegion(col("client_ip")))
      .withColumn("isp",Functions.ipToIsp(col("client_ip")))
      .withColumn("request_time",col("request_time") cast("timestamp"))
      .withColumn("process_time",current_timestamp())

    format = waterMark.map(w => format.withWatermark(c,w)).getOrElse(format)

    format.explain

    format.createOrReplaceTempView("format")
  }

}
