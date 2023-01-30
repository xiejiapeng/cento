package main.scala.com.qiniu.cdn.dw

import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.util.Bytes

class DistributedScan(split: Int, scan: Scan) {
  val startRow = scan.getStartRow
  val stopRow = scan.getStopRow

  lazy val distributed = distributeScan(startRow,stopRow)

  def distributeScan(start: Array[Byte], stop: Array[Byte]) : Seq[Scan] = {
    Array.range(0,split).map(i => {
      val s1 = Bytes.add(Array(i.toByte),start)
      val s2 = Bytes.add(Array(i.toByte),stop)
      new Scan(scan).withStartRow(s1).withStopRow(s2)
    })
  }
}

object DistributedScan {
  def apply(split: Int, scan: Scan) : DistributedScan = {
    DistributedScan(split, scan)
  }
}
