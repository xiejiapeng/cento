package main.scala.com.qiniu.cdn.dw.hbase

import org.apache.spark.sql.connector.catalog.{Table, TableProvider}
import org.apache.spark.sql.connector.expressions.Transform
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import java.util

class HbaseTableProvider extends TableProvider{
  override def inferSchema(caseInsensitiveStringMap: CaseInsensitiveStringMap): StructType = ???

  override def getTable(structType: StructType, transforms: Array[Transform], map: util.Map[String, String]): Table = ???
}
