package collection.set;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortSet {
    public void treeSet(){
        //TreeSet内部封装了TreeMap，所以它的底层实现都依赖于TreeMap的排序能力
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);
        ts.add(2);
        ts.add(5);
        ts.add(2);
        //无论如何，treeSet还是属于Set，除了排序的功能，它还有去重的功能
        System.out.println("size: " + ts.size());

        System.out.println("first: " + ts.first());
        System.out.println("last: " + ts.last());
        System.out.println("celling-3: " + ts.ceiling(3));
        System.out.println("floor-3: " + ts.floor(3));

        SortedSet<Integer> head = ts.headSet(3);
        System.out.println("first of head-3 set: " + head.first());
        System.out.println("last of head-3 set: " + head.last());

        SortedSet<Integer> tail = ts.tailSet(3);
        System.out.println("first of tail-3 set: " + tail.first());
        System.out.println("last of tail-3 set: " + tail.last());
    }

    public static void main(String[] args) {
        SortSet s = new SortSet();
        s.treeSet();
    }
}
