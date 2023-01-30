package main.scala.com.qiniu.cdn.analyze

import com.qiniu.cdn.Functions
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{countDistinct, row_number}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object OfflineAnalyze {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("douyin-offline-ana").enableHiveSupport().getOrCreate()
    Functions.register(spark)

    import spark.implicits._

    //top分析 domain+time range+region => {top region, top url, top ip}
    val dataset = spark.sql("select * from kodofs2.rtlog where day = '20220821' and hour = '06' and domain = 'v18.toutiaovod.com'")
    dataset.createOrReplaceTempView("dataset")

    //domain+day+region => {top region}
    val topRegionSql =
      """
        |select domain,day,client_region,sum(bytes_sent) as bytes_sent, count(*) as count
        |from dataset
        |group by domain,day,client_region
        |""".stripMargin
    val topRegion = spark.sql(topRegionSql)

    val topRegionTraffic = topRegion.sort($"bytes_sent".desc).limit(100)
    save(topRegionTraffic, "topRegionTraffic")

    val topRegionCount = topRegion.sort($"count".desc).limit(100)
    save(topRegionCount, "topRegionCount")


    //domain+day+region+url => {top url}
    val topUrlSql =
      """
        |select domain,day,client_region,url,sum(bytes_sent) as bytes_sent, count(*) as count
        |from dataset
        |group by domain,day,client_region,url
        |""".stripMargin
    val topUrl = spark.sql(topUrlSql).cache()

    val ut = Window.partitionBy($"domain", $"day", $"client_region").orderBy($"bytes_sent".desc)
    val topUrlTraffic = topUrl.withColumn("rn", row_number.over(ut)).where($"rn" <= 100).drop($"rn")
    save(topUrlTraffic, "topUrlTraffic")

    val uc = Window.partitionBy($"domain", $"day", $"client_region").orderBy($"count".desc)
    val topUrlCount = topUrl.withColumn("rn", row_number.over(uc)).where($"rn" <= 500).drop($"rn")
    save(topUrlCount, "topUrlCount")
    topUrl.unpersist()

    //domain+day+region+ip => {top ip}
    val topIpSql =
      """
        |select domain,day,client_region,client_ip,sum(bytes_sent) as bytes_sent, count(*) as count
        |from dataset
        |group by domain,day,client_region,client_ip
        |""".stripMargin
    val topIp = spark.sql(topIpSql).cache()

    val it = Window.partitionBy($"domain", $"day", $"client_region").orderBy($"bytes_sent".desc)
    val topIpTraffic = topIp.withColumn("rn", row_number.over(it)).where($"rn" <= 500).drop($"rn")
    save(topIpTraffic, "topIpTraffic")

    val ic = Window.partitionBy($"domain", $"day", $"client_region").orderBy($"count".desc)
    val topIpCount = topIp.withColumn("rn", row_number.over(ic)).where($"rn" <= 500).drop($"rn")
    save(topIpCount, "topIpCount")
    topIp.unpersist()

    val count = dataset.groupBy($"domain", $"day").agg(countDistinct("client_ip"))
    save(count, "ua")

  }

  def save(ds: DataFrame, path: String): Unit = {
    ds.coalesce(10).write.format("csv")
      .mode(SaveMode.Overwrite)
      .option("header", "true")
      .save(s"/tmp/implement/$path")
  }
}
