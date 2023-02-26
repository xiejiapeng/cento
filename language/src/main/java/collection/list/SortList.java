package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortList {
    public static void main(String[] args) {
        SortList s = new SortList();
        s.sortWithUtil();
    }

    public void sortWithUtil() {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(6);
        a.add(3);

        /*
            collections的Sort方法是将list转化为一个Array，最终调用的还是Arrays的Sort方法
            Sort的过程为：
                如果配置了强行使用mergesort，则使用mergesort
                否则使用timsort
            当数组部分有序的时候，timsort的时间复杂度接近于O(n)
         */
        Collections.sort(a);
        System.out.println(a);

        /*
            jdk本身不提供sortedList数据结构，list的使用场景是能通过index获取到元素，所以对list进行自动排序
            是没有意义的。如果一定需要排序，可以使用treeSet或者priorityQueue
         */

    }
}
