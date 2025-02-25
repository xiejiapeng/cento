package apis

import org.apache.spark.sql.functions.{expr, sum}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}

object Agg {
  def main(args: Array[String]) : Unit = {
    sql()
  }

  def dataset() : Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .getOrCreate()

    val data = Seq((1, "Alice", 10), (2, "Bob", 20), (3, "Cathy", 30), (4, "David", 40))
    import spark.implicits._

    val df = spark.createDataset(data).toDF("id","name","score")
    val agg = df.groupBy(expr("id % 2")).agg(sum("score").alias("total_age"))
    agg.show(100, false)
  }
  def sql() : Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .getOrCreate()

    val data = Seq((1, "Alice", 10), (2, "Bob", 20), (3, "Cathy", 30), (4, "David", 40))

    val df = spark.createDataFrame(data).toDF("id", "name", "score")
    df.createOrReplaceTempView("students")
    spark.sql("select id%2,sum(score) as total_score from students group by id % 2").show(100, false)
  }

  def rdd(): Unit = {
    val conf = new SparkConf().setAppName("sort_by_key").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data = Seq((1, "Alice", 10), (2, "Bob", 20), (3, "Cathy", 30), (4, "David", 40))
    val rdd = sc.parallelize(data)

    val rdd2 = rdd.map(x => (x._1 % 2, (x._1, x._2, x._3)))
      .reduceByKey((x,y) => (x._1, x._2, x._3 + y._3))
      .map(x => (x._1, x._2._3))

    rdd2.collect().foreach(println)

    sc.stop()
  }
}
