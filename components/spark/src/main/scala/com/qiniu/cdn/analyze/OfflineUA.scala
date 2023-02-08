package com.qiniu.cdn.analyze

import com.qiniu.cdn.{Commons, Functions}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import java.util
import java.util.{ArrayList, List}

object OfflineUA {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("douyin-offline-ana-2").enableHiveSupport().getOrCreate()
    Functions.register(spark)

    import spark.implicits._

    val dataset = spark.sql(s"select * from cdn.rtlog where day >= '${Commons.startDay}' and day < '${Commons.endDay}' and hour = '06' and domain = '${Commons.filteredDomain}'")

    val count = dataset.groupBy($"domain", $"day", $"client_ip").count()

    save(count, "ua")
  }

  def save(ds: DataFrame, path: String): Unit = {
    ds.write.format("csv")
      .mode(SaveMode.Overwrite)
      .option("header", "true")
      .save(s"/tmp/implement/$path")
  }

  def longestCommonSubsequence(text1: String, text2: String): Int = {
    val l: util.List[Integer] = new util.ArrayList[Integer]
    for(i <- 0 until text1.length) {
      l.add(text2.indexOf(text1.charAt(i)))
    }

    0
  }

  def lis(list: util.List[Int]): Unit ={
    val l = new util.ArrayList[Int]()
    val next = 0



  }
}
