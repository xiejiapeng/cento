package apis

import org.apache.spark.sql.SparkSession

object Window {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("window_function_test")
      .getOrCreate()

    val df = Utils.prepare(spark)
    df.createOrReplaceTempView("students")
    row(spark)
  }

  def row(spark: SparkSession): Unit = {
    val sql =
      """
        |select name,
        | age,
        | address,
        | row_number() over(partition by address order by age) as row
        |from students
        |""".stripMargin
    println("run in row function")
    spark.sql(sql).show(100, false)
    val df = spark.sql(sql)
    println("analyzed of row_number()")
    Utils.analyzed(spark, spark.sessionState.analyzer, df)
  }
}
