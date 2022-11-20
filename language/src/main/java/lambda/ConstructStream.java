package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
可以参考
https://blogs.oracle.com/javamagazine/post/java-parallel-streams-performance-benchmark
 */
public class ConstructStream {
    private static Stream<Integer> generateInts(int start, int end) {
        /*
        range方法生成Spliterator，作为一个pipeline的source。我们使用的流Stream一般都是BaseStream的子类，而AbstractPipeline是
        BaseStream的子类。多个AbstractPipeline构成一个链表，表示对source的一系列操作。

        AbstractPipeline的head拥有Spliterator属性，用于生成数据
         */
        return IntStream.range(start, end).boxed();
    }

    private static void showFunctions() {
        Stream<Integer> nums = generateInts(0, 100);
        /*
        通过parallel方法可以将这个pipeline的source stage的parallel属性设置为true。如果是parallel模式，执行的时候是转化为一个ForkJoinTask调度给线程池
        执行；如果不是parallel模式，而是sequential模式，本质是通过Spliterator的foreachRemaining进行顺序执行

        stream构造出来以后往往都不是parallel的，除非Collections的parallelStream会产生parallel的stream
        parallel并不一定会增加性能，因为线程调度本身也有一定开销，还有进行split的时候也会产生开销，以下情况下一般可以加速
            - 数据量比较大
            - 计算密集型的，计算量比较大
            - 比较容易split的流
         */
        nums.map(i -> i * 2).parallel().forEach(System.out::println);

        /*
        limit,distinct,skip等都会产生一个有状态的Ops,比如distinct会使用concurrentHashMap保存已经遇到过的元素，limit会使用ArrayBuffer保存遇到过的元素；
        在这种有状态的节点上面进行parallel往往有额外的开销，一定程度会影响到性能
         */
        nums.limit(10);
        nums.distinct();
    }

    private static void collectionStream() {
        List<String> eles = Arrays.asList("1", "2", "3");
        //构造一个天然的parallel的流
        eles.parallelStream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        showFunctions();
    }
}
