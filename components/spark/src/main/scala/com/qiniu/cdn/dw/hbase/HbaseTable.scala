package main.scala.com.qiniu.cdn.dw.hbase

import com.qiniu.cdn.dw.DistributedScan
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Result}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.connector.catalog.{SupportsRead, Table, TableCapability}
import org.apache.spark.sql.connector.read.{Batch, InputPartition, PartitionReader, PartitionReaderFactory, Scan, ScanBuilder}
import org.apache.spark.sql.types.{BinaryType, StructField, StructType}
import org.apache.spark.sql.util.CaseInsensitiveStringMap

import scala.collection.JavaConverters._
import java.util

/**
 * hbase table
 * @param sparkSession
 * @param tableName
 */
class HbaseTable(sparkSession: SparkSession, conf: Configuration, tableName: String, split: Int, scan: org.apache.hadoop.hbase.client.Scan, qualifiers: Option[Array[(String,String)]]) extends Table with SupportsRead{
  override def name(): String = "hbase"

  val tableSchema = schema()
  val connection = ConnectionFactory.createConnection(conf)

  /*
   schema: row: BinaryType, q1: BinaryType...
   */
  override def schema(): StructType = {
    val schema = Array(StructField("rowKey",BinaryType))
    qualifiers.map(qs => qs.foreach {
      case (cf, qualifier) => schema :+ StructField(cf+":"+qualifier,BinaryType)
    })
    StructType(schema)
  }

  override def capabilities(): util.Set[TableCapability] = Set(TableCapability.BATCH_READ).asJava

  override def newScanBuilder(caseInsensitiveStringMap: CaseInsensitiveStringMap): ScanBuilder = new HbaseScanBuilder(tableSchema, connection, split, scan, tableName)
}

class HbaseScanBuilder(schema: StructType, connection: Connection, split: Int, scan: org.apache.hadoop.hbase.client.Scan, tableName: String) extends ScanBuilder {
  override def build(): Scan = new HbaseScan(schema, split, scan, connection, tableName)
}

class HbaseScan(schema: StructType, split: Int, scan: org.apache.hadoop.hbase.client.Scan, connection: Connection, tableName: String) extends Scan with Batch{
  val distributedScan = DistributedScan(split, scan)


  override def readSchema(): StructType = schema

  override def planInputPartitions(): Array[InputPartition] = {
    distributedScan.distributed.map(scan => {
      new HbaseBatchInputPartition(connection, tableName, scan)
    }).toArray
  }

  override def createReaderFactory(): PartitionReaderFactory = {
    new HbaseBatchReaderFactory(schema, connection, tableName)
  }
}

class HbaseBatchInputPartition(val connection: Connection, val tableName: String, val scan: org.apache.hadoop.hbase.client.Scan) extends InputPartition {

}

class HbaseBatchReaderFactory(schema: StructType, connection: Connection, tableName: String) extends PartitionReaderFactory {
  override def createReader(inputPartition: InputPartition): PartitionReader[InternalRow] = {
    val p = inputPartition.asInstanceOf[HbaseBatchInputPartition]
    val scan = p.scan
    val hTable = connection.getTable(TableName.valueOf(tableName))
    val resultScanner = hTable.getScanner(scan)
    val resultIter = resultScanner.iterator()
    new PartitionReader[InternalRow] {
      override def next(): Boolean = {
        if(resultIter.hasNext){
          true
        } else {
          resultScanner.close()
          false
        }
      }

      override def get(): InternalRow = toInternalRow(resultScanner.next())

      override def close(): Unit = ???
    }
  }

  val toInternalRow: Result =>  InternalRow = {
    (result: Result) => {
      val rowKey = result.getRow
      val data = schema.filter(s => !s.name.equals("rowKey"))
        .foreach(s => {
          val cf = s.name.split(":")(0)
          val qualifier = s.name.split(":")(1)
          result.getColumnCells(Bytes.toBytes(cf),Bytes.toBytes(qualifier)).get(0).getValueArray
        })
      data +: rowKey
      InternalRow(data)
    }
  }
}
