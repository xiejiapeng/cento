package collection.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
    public void concurrentHashMap(){
        ConcurrentHashMap<Integer,Integer> chm = new ConcurrentHashMap<>();
        /*
            对hashcode进行了spread，而不是直接使用对象本身的hashcode。spread是指将hashcode的高16位的比特与
            低16位进行异或。某些对象比如Float的hashcode低比特位是相同的，只是高比特位不同。这样模mask的时候只有
            低比特位发挥作用，很容易造成碰撞。spread当然不能完全避免这个问题，但是大多数对象的hashcode都是均匀分布的，
            所以使用了这种最cheap的方式做spread，兼顾了性能。（判断是不是2的幂，x & (x-1) == 0?）
         */
        chm.put(1,3);
        chm.put(2,6);
        /*
            size的统计通过baseCount和counterCell联合计算而出，如果在更新count的时候发生线程竞争
            的现象(即cas失败），则在counterCell数组中更新count，这样能提高并行度。

            size()以整数形式返回size，如果超过Integer.MAX_VALUE，则返回Integer.MAX_VALUE
            mappingCount()以Long形式返回size，可以超过最大整数值
         */
        System.out.println("size: " + chm.size());
        System.out.println("mapping count: " + chm.mappingCount());
    }

    public static void main(String[] args) {
        ConcurrentMap c = new ConcurrentMap();
        c.concurrentHashMap();
    }
}
