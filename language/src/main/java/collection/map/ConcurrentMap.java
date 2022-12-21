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
        /*
            在put的时候有几个关注点
            1、hashcode上面已经介绍
            2、put的整体流程是外面一个循环，内部使用cas。
            3、如果正在进行resize，则helptransfer
            4、如果hashcode对应的桶为空，则构造一个新桶就结束了
            5、否则用synchronized锁住桶的head，然后再遍历这个桶内元素，进行修改或者新增
            6、遍历的时候记录binCount，然后判断是否需要treeiFy
            7、最终更新总count数目，同时判断resize
         */
        chm.put(2,6);
        /*
            size的统计通过baseCount和counterCell联合计算而出，如果在更新count的时候发生线程竞争
            的现象(即cas失败），则在counterCell数组中更新count，这样能提高并行度。

            size()以整数形式返回size，如果超过Integer.MAX_VALUE，则返回Integer.MAX_VALUE
            mappingCount()以Long形式返回size，可以超过最大整数值
         */
        System.out.println("size: " + chm.size());

        /*
            更新size的流程
            - 先判断counterCell数组是否为空，不为空的话说明已经发生过线程竞争，则直接更新countercell
            - 否则cas尝试更新chm的baseCount字段，如果没有线程竞争的话，可以更新成功，否则更新countercell
            - 通过cas更新countercell内部的value字段，如果没有更新成功，则进入fullAddCount阶段
         */
        System.out.println("mapping count: " + chm.mappingCount());
    }

    public static void main(String[] args) {
        ConcurrentMap c = new ConcurrentMap();
        c.concurrentHashMap();
    }
}
