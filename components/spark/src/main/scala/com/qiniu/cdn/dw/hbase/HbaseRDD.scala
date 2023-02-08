package com.qiniu.cdn.dw.hbase

import com.qiniu.cdn.dw.DistributedScan
import com.qiniu.defy.commons.common.avro.StandardizedLog
import org.apache.avro.file.{DataFileReader, SeekableByteArrayInput}
import org.apache.avro.specific.SpecificDatumReader
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.{Connection, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, SparkContext, TaskContext}

import scala.collection.JavaConverters._

class HbaseRDD(sc:SparkContext
               , split: Int
               , connection: Connection
               , table: String
               , family: String
               , qualifier: String
               , scan: Scan) extends RDD[StandardizedLog](sc,Nil){
  val htable = connection.getTable(TableName.valueOf(table))
  val distributedScan = DistributedScan(split,scan)

  override def compute(split: Partition, context: TaskContext): Iterator[StandardizedLog] = {
    val hbaseSplit = split.asInstanceOf[HbasePartition]
    val resultScanner = htable.getScanner(hbaseSplit.scan)

    new Iterator[StandardizedLog] {
      var innerIter : Iterator[StandardizedLog] = null
      val datumReader = new SpecificDatumReader[StandardizedLog](StandardizedLog.getClassSchema)

      override def hasNext: Boolean = {
        if(innerIter == null || !innerIter.hasNext){
          val result = resultScanner.next()
          val bytes = result.getColumnCells(Bytes.toBytes(family), Bytes.toBytes(qualifier)).get(0).getValueArray
          val dataFileReader = new DataFileReader[StandardizedLog](new SeekableByteArrayInput(bytes), datumReader)
          innerIter = dataFileReader.iterator().asScala
        }
        innerIter.hasNext
      }

      override def next(): StandardizedLog = {
        innerIter.next()
      }
    }
  }

  override protected def getPartitions: Array[Partition] = {
    distributedScan.distributed.zipWithIndex.map(s => new HbasePartition(s._2,s._1,connection))
      .toArray
  }
}

class HbasePartition(id: Int, val scan: Scan, val connection: Connection) extends Partition {
  override def index: Int = id
}
