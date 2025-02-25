package apis

import org.apache.spark.sql.functions.{expr, sum}
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}

object Sort {
  def main(args: Array[String]) : Unit = {
    sql
  }

  def dataset() : Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .getOrCreate()

    val data = Seq((1, "Alice", 10), (3, "Cathy", 30), (2, "Bob", 20), (4, "David", 40))
    import spark.implicits._

    val df = spark.createDataset(data).toDF("id","name","score")
    val sort = df.sort("score")
    sort.show(100, false)
  }
  def sql() : Unit = {
    val spark = SparkSession.builder()
      .config(SQLConf.ADAPTIVE_EXECUTION_ENABLED.key,"false")
      .master("local[*]")
      .getOrCreate()

    val data = Seq((1, "Alice", 10), (3, "Cathy", 30), (2, "Bob", 20), (4, "David", 40))

    val df = spark.createDataFrame(data).toDF("id", "name", "score")
    df.createOrReplaceTempView("students")
    val x = spark.sql("select * from students order by score")
    println(x.queryExecution.executedPlan)

//    Utils.analyzed(spark, spark.sessionState.analyzer, x)
    Utils.optimized(spark, spark.sessionState.optimizer, x)
  }

  def rdd(): Unit = {
    val conf = new SparkConf().setAppName("sort_by_key").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data = Seq((1, "Alice", 10), (2, "Bob", 20), (3, "Cathy", 30), (4, "David", 40))
    val rdd = sc.parallelize(data)

    val rdd2 = rdd.sortBy(_._2)

    rdd2.collect().foreach(println)

    sc.stop()
  }
}
