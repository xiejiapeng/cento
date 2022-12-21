package com.twitter.client.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.Iterator;

public class HbaseRepo {
    Connection connection;
    public HbaseRepo() throws IOException {
        Configuration configuration = new Configuration();
        configuration.addResource("");
        connection = ConnectionFactory.createConnection(configuration);
    }

    public Table getTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        return connection.getTable(tableName);
    }

    public void doPut(String tableName, byte[] rowKey, byte[] family, byte[] qualifier, byte[] value) throws IOException {
        Put put = new Put(rowKey);
        put.addColumn(family,qualifier,value);
        Table table = getTable(tableName);
        table.put(put);
    }

    public void doGet(String tableName, byte[] rowKey, byte[] family, byte[] qualifier) throws IOException {
        Get get = new Get(rowKey);
        get.addColumn(family,qualifier);
        Table table = getTable(tableName);
        /* 注意，下面这种写法不可取，会带来多次的rpc，每次get都会带来一次！*/
        //通过binarySearch查找原始的cell
        table.get(get).getValue(family,qualifier);
        //将原始cell 的内容复制到一个新的字节map中，family -> qualifier -> version -> value，一共三层map，每一次map都是一个TreeMap结构
        table.get(get).getMap();
        //去掉version这一层的map，选取最大的version，即timestamp
        table.get(get).getNoVersionMap();
        //获取某个family对应的map
        table.get(get).getFamilyMap(family);
    }

    public void doScan(String tableName, byte[] startKey, byte[] endKey, byte[] family, byte[] qualifier) throws IOException {
        Scan scan = new Scan(startKey, endKey);
        scan.addColumn(family, qualifier);
        Table table = getTable(tableName);
        //stream的并行化一定比串行化更快吗？不一定，需要split，带来一定代价
        Iterator<Result> resultIterator = table.getScanner(scan).iterator();
        while (resultIterator.hasNext()) {
            Result result = resultIterator.next();
            result.getMap();
        }
    }

    /*
        可以关注一下server端是怎么实现increment的
        sever端需要获取到行锁才能继续执行
     */
    public void increase(String tableName, byte[] row, byte[] family, byte[] qualifier, long delta) throws IOException {
        Table table = getTable(tableName);
        table.incrementColumnValue(row, family, qualifier, delta);
        table.incrementColumnValue(row, family, qualifier, delta, Durability.ASYNC_WAL);
        Increment increment = new Increment(row);
        increment.addColumn(family,qualifier,delta);
        table.increment(increment);
    }
}
