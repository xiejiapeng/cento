package main.scala.com.qiniu.cdn.dw.dwm

import com.qiniu.cdn.Commons
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger
import org.apache.spark.sql.streaming.{OutputMode, StreamingQueryListener}
import org.apache.spark.sql.functions._

object PreCal {
  def main(args:Array[String]):Unit = {
    val spark = SparkSession.builder().appName("douyin-pre").getOrCreate()
    import spark.implicits._

    Commons.registerView(spark,"cdnlog-raw-0",None)

    val preSql =
      """
        |select domain,cdn_provider,window(request_time,'1 minutes'),hitmiss,status_code,sum(bytes_sent) as bytes_sent,count(*) as count
        |from format
        |group by domain,cdn_provider,window(request_time,'1 minutes'),hitmiss,status_code
        |""".stripMargin

    val preTable = spark.sql(preSql)
      .select(to_json(struct($"domain",$"cdn_provider",$"window.start",$"hitmiss",$"status_code",$"bytes_sent",$"count")) as "value")

    val query = preTable.writeStream
      .trigger(ProcessingTimeTrigger(0))
      .format("kafka")
      .outputMode(OutputMode.Update())
      .options(Map(
        ("kafka.bootstrap.servers","jjh645:19092")
        ,("topic",Commons.topic_pre_cal),("checkpointLocation","/tmp/implement/ck/pre_cal")
        ,("maxOffsetsPerTrigger","1000000")))
      .start()

    spark.streams.addListener(new StreamingQueryListener {
      override def onQueryStarted(event: StreamingQueryListener.QueryStartedEvent): Unit = ???

      override def onQueryProgress(event: StreamingQueryListener.QueryProgressEvent): Unit = ???

      override def onQueryTerminated(event: StreamingQueryListener.QueryTerminatedEvent): Unit = ???
    })

    spark.streams.awaitAnyTermination()
  }
}
