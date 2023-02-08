package com.qiniu.cdn.analyze

import com.qiniu.cdn.{Commons, Functions}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger
import org.apache.spark.sql.functions.{struct, to_json}
import org.apache.spark.sql.streaming.OutputMode

object EdgeAnalyze {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("douyin-online-ana").getOrCreate()
    Functions.register(spark)

    Commons.registerView(spark, "cdnlog-raw-0", Some("1 hour"))

    //针对每个域名+5分钟+供应商+区域+运营商 => {流量之和，日志条数}
    val trafficSql =
      """
        |select domain,cdn_provider,window(request_time,'5 minutes'), region, isp, sum(bytes_sent) as bytes_sent, count(*) as count
        |from format
        |group by domain,cdn_provider,window(request_time,'5 minutes'),region,isp
        |grouping sets(
        | (domain,cdn_provider,window(request_time,'5 minutes'))
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),region)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),isp)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),region,isp)
        |)
        |""".stripMargin
    val aggTraffic = spark.sql(trafficSql)
      .select(to_json(struct("domain", "cdn_provider", "window.start", "region", "isp", "bytes_sent", "count")) as "value")

    aggTraffic.writeStream
      .queryName("agg_traffic")
      .outputMode(OutputMode.Update())
      .format("kafka")
      .trigger(ProcessingTimeTrigger(0l))
      .options(Map(
        ("kafka.bootstrap.servers", "jjh645:19092")
        , ("topic", Commons.topic_edge_traffic), ("checkpointLocation", "/tmp/implement/ck/agg_traffic")
        , ("maxOffsetsPerTrigger", "1000000")))
      .start()


    //针对每个域名+5分钟+供应商+区域+运营商 => {日志条数}
    val codeSql =
      """
        |select domain,cdn_provider,window(request_time,'5 minutes'),region,isp,status_code,count(*) as count
        |from format
        |group by domain,cdn_provider,window(request_time,'5 minutes'),region,isp,status_code
        |grouping sets(
        | (domain,cdn_provider,window(request_time,'5 minutes'),status_code)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),status_code,region)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),status_code,isp)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),status_code,region,isp)
        |)
        |""".stripMargin
    val aggCode = spark.sql(codeSql)
      .select(to_json(struct("domain", "cdn_provider", "window.start", "region", "isp", "status_code", "count")) as "value")
    aggCode.writeStream
      .queryName("agg_code")
      .trigger(ProcessingTimeTrigger(0l))
      .format("kafka")
      .outputMode(OutputMode.Update)
      .options(Map(
        ("kafka.bootstrap.servers", "jjh645:19092")
        , ("topic", Commons.topic_edge_code), ("checkpointLocation", "/tmp/implement/ck/agg_code")
        , ("maxOffsetsPerTrigger", "1000000")))
      .start()


    //针对每个域名+5分钟+供应商+区域+运营商+命中 => {流量之和，日志条数}
    val hitSql =
      """
        |select domain,cdn_provider,window(request_time,'5 minutes'),region,isp,hitmiss,sum(bytes_sent) as bytes_sent, count(*) as count
        |from format
        |group by domain,cdn_provider,window(request_time,'5 minutes'),region,isp,hitmiss
        |grouping sets(
        | (domain,cdn_provider,window(request_time,'5 minutes'),hitmiss)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),hitmiss,region)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),hitmiss,isp)
        | ,(domain,cdn_provider,window(request_time,'5 minutes'),hitmiss,region,isp)
        |)
        |""".stripMargin

    val aggHit = spark.sql(hitSql)
      .select(to_json(struct("domain", "cdn_provider", "window.start", "region", "isp", "hitmiss", "bytes_sent", "count")) as "value")
    aggHit.writeStream
      .queryName("agg_hit")
      .trigger(ProcessingTimeTrigger(0l))
      .format("kafka")
      .outputMode(OutputMode.Update)
      .options(Map(
        ("kafka.bootstrap.servers", "jjh645:19092")
        , ("topic", Commons.topic_edge_code), ("checkpointLocation", "/tmp/implement/ck/agg_hit")
        , ("maxOffsetsPerTrigger", "1000000")))
      .start()

    spark.streams.awaitAnyTermination()
  }
}
