package apis

import org.apache.spark.sql.catalyst.analysis.Analyzer
import org.apache.spark.sql.catalyst.optimizer.Optimizer
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.catalyst.plans.logical.{GlobalLimit, InsertIntoStatement, LogicalPlan}
import org.apache.spark.sql.catalyst.rules.RuleExecutor
import org.apache.spark.sql.errors.QueryExecutionErrors
import org.apache.spark.util.Utils

import scala.reflect.runtime.universe

/*
1  2
| / |
4   3

 */


object Utils {
  def analyzed(spark: SparkSession, analyzer: Analyzer, df: DataFrame): Unit = {
    spark.sessionState.analyzer
    var logic = df.queryExecution.logical
    val batches = analyzer.batches
    batches.foreach(batch => {
      var curPlan = logic
      var lastPlan = logic
      var continue = true
      while (continue) {
        curPlan = batch.rules.foldLeft(curPlan) {
          case (plan, rule) =>
            val result = rule(plan)
            if(!result.fastEquals(plan)) {
              println(s"batch is ${batch.name}, rule is ${rule.ruleName} + before is $plan + after is $result")
            }
            result
        }
        if (curPlan.fastEquals(lastPlan)) {
          continue = false
        }
        lastPlan = curPlan
      }
      if(!curPlan.fastEquals(logic)) {
//        if(batch.name.equals("Resolution")) {
//          val insert = curPlan.asInstanceOf[InsertIntoStatement]
//          val relation = insert.table
//          println("relation type is " + relation.getClass)
//        }
        println("find here, rule is " + batch.name + ", before is\n " + logic + ", after is\n " + curPlan + ", batch is " + batch.name)
      }
      logic = curPlan
    })
  }

  def optimized(spark: SparkSession, optimizer: Optimizer, df: DataFrame): Unit = {
    var logic = df.queryExecution.analyzed
    val batches = optimizer.batches
    batches.foreach(batch => {
      if(batch.name == "LocalRelation early") {
        println("f")
      }
      var curPlan = logic
      var lastPlan = logic
      var continue = true
      while (continue) {
        curPlan = batch.rules.foldLeft(curPlan) {
          case (plan, rule) =>
            val result = rule(plan)

            result
        }
        if (curPlan.fastEquals(lastPlan)) {
          continue = false
        }
        lastPlan = curPlan
      }
      if(!curPlan.fastEquals(logic)) {
        println("find here, rule is " + batch.name + ", before is\n " + logic + ", after is\n " + curPlan + ", batch is " + batch.name)
      }
      logic = curPlan
    })
  }


}
