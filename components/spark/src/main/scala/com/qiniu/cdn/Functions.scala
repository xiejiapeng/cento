package com.qiniu.cdn

import org.apache.spark.sql.{SparkSession, functions => F}

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter
import java.util.Calendar
import scala.util.Random

object Functions {
  val regions = Seq("jiangsu","shanghai","zhejiang","guangdong","shanxi")
  val isps = Seq("mobile","telcon","tietong","pengboshi")

  val ipToRegion = F.udf((ip:String) => {
    val r = Random.nextInt(regions.length)
    regions(r)
  })

  val ipToIsp = F.udf((ip:String) => {
    val r = Random.nextInt(isps.length)
    isps(r)
  })

  val five_minute = (timestamp: Timestamp) => {
    val cal = Calendar.getInstance()
    cal.setTime(timestamp)
    cal.get(Calendar.MINUTE) / 5 * 5
  }

  val hour = (timestamp: Timestamp) => {
    val cal = Calendar.getInstance()
    cal.setTime(timestamp)
    cal.get(Calendar.HOUR_OF_DAY)
  }

  val day = (timestamp: Timestamp) => {
    val sdf = new SimpleDateFormat("yyyyMMdd")
    sdf.format(timestamp)
  }

  //2022-08-30 16:10:00
  val strToTime = (logTime: String, pattern:String) => {
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
    Timestamp.from(Instant.from(formatter.parse(logTime))).getTime / 1000
  }

  val timeToStr = (logTime : Timestamp) => {
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.format(logTime)
  }

  def register(spark:SparkSession): Unit ={
    spark.udf.register("ipToRegion",ipToRegion)
    spark.udf.register("ipToIsp",ipToIsp)
    spark.udf.register("five_minuteC",five_minute)
    spark.udf.register("hourC",hour)
    spark.udf.register("dayC",day)
    spark.udf.register("strToTime", strToTime)
    spark.udf.register("timeToStr", timeToStr)
  }
}
