package collection.list;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentList {
    public void copyOnWrite(){
        CopyOnWriteArrayList<String> cow = new CopyOnWriteArrayList<>();
        /*
            cow在写的时候加锁，然后复制一份原数组的数据。在写完之后set回去（所有的有修改的，包括排序操作都是这种方式）
            cow不适合写的太多的场景

            cow的iterator无法对原数据进行修改
         */
        cow.add("abc");
        cow.add("def");
        /*
            cow在读的时候不进行任何安全操作，不需要加锁，就是在原数组上面获取
         */
        System.out.println(cow.get(0));
        System.out.println(cow);
        cow.set(1, "xyz");
        System.out.println(cow);
    }

    public static void main(String[] args) {
        ConcurrentList c = new ConcurrentList();
        c.copyOnWrite();
    }
}
