package com.qiniu.cdn.starrocks

import org.apache.spark.sql.SparkSession

/*
scala> spark.sql("describe table module.post").show(100,false)
+-----------------------+---------+-------+
|col_name               |data_type|comment|
+-----------------------+---------+-------+
|cdn                    |string   |null   |
|domain                 |string   |null   |
|flux                   |bigint   |null   |
|logTime                |string   |null   |
|moduleName             |string   |null   |
|region                 |string   |null   |
|uid                    |bigint   |null   |
|day                    |string   |null   |
|hour                   |int      |null   |
|# Partition Information|         |       |
|# col_name             |data_type|comment|
|day                    |string   |null   |
|hour                   |int      |null   |
+-----------------------+---------+-------+
 */
object PostEtl {
    def main(args:Array[String]): Unit ={
      val spark = SparkSession.builder().enableHiveSupport().appName("post-etl").getOrCreate()

      val post = spark.table("module.post")


    }
}
