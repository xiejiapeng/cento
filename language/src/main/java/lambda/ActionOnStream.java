package lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class ActionOnStream {
    //collect 对数据进行聚合，聚合可以是聚合到一个集合中，可以聚合成一个最终值等等。只需要定义好对accumulator的操作即可
    private static void collect(){
        Stream<Integer> stream = ConstructStream.generateInts(0,100);
        double avg = stream.collect(new Collector<Integer, int[], Double>() {
            @Override
            public Supplier<int[]> supplier() {
                return () -> new int[2];
            }

            @Override
            public BiConsumer<int[], Integer> accumulator() {
                return (acc,x) -> {
                    int sum = acc[0];
                    int count = acc[1];
                    acc[0] = sum + x;
                    acc[1] = count + 1;
                };
            }

            @Override
            public BinaryOperator<int[]> combiner() {
                return (ints, ints2) -> new int[]{ints[0]+ints2[0], ints[1]+ints2[1]};
            }

            @Override
            public Function<int[], Double> finisher() {
                return (acc) -> (double)acc[0] / acc[1];
            }

            @Override
            public Set<Characteristics> characteristics() {
                Set<Characteristics> cs = new HashSet<>();
                cs.add(Characteristics.UNORDERED);
                return cs;
            }
        });

        stream = Arrays.asList(1,1,2,2,3,3).stream();
        Map<Integer,Integer> m = stream.collect(new Collector<Integer, Map<Integer,Integer>, Map<Integer,Integer>>() {
            @Override
            public Supplier<Map<Integer, Integer>> supplier() {
                return () -> new HashMap<>();
            }

            @Override
            public BiConsumer<Map<Integer, Integer>, Integer> accumulator() {
                return (m, x) -> {
                    m.put(x, m.getOrDefault(x, 0) + 1);
                };
            }

            @Override
            public BinaryOperator<Map<Integer, Integer>> combiner() {
                return (m,n) -> {
                    Map<Integer,Integer> ans = new HashMap<>();
                    for(int x : m.keySet())ans.put(x, m.get(x));
                    for(int y : n.keySet())ans.put(y, ans.getOrDefault(y,0) + n.get(y));
                    return ans;
                };
            }

            @Override
            public Function<Map<Integer, Integer>, Map<Integer, Integer>> finisher() {
                return e -> e;
            }

            @Override
            public Set<Characteristics> characteristics() {
                Set<Characteristics> cs = new HashSet<>();
                cs.add(Characteristics.UNORDERED);
                return cs;
            }
        });

        System.out.println(avg);
        System.out.println(m);
    }

    public static void main(String[] args) {
        collect();
    }
}
