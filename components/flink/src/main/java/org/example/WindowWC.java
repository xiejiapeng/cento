package org.example;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.spark.sql.sources.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WindowWC {
    private static final Logger logger = LoggerFactory.getLogger(WindowWC.class);
    public static class Mapper implements MapFunction<String, Tuple2<Long, String>> {
        @Override
        public Tuple2<Long, String> map(String s) {
            String[] ws = s.split(",");
            //2024.04.10 22:00 + seconds
            return new Tuple2<>(1712757600000L + 1000*Long.parseLong(ws[0]), ws[1]);
        }
    }
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(2);
        DataStreamSource<String> source = environment.socketTextStream("127.0.0.1", 30001);
        WatermarkStrategy<String> watermarkStrategy = WatermarkStrategy
        .<String>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                .withTimestampAssigner((event, timestamp) -> {
//                    logger.info("timestamp assigned to {}", event);
                    String[] ws = event.split(",");
                    return 1712757600000L + 1000*Long.parseLong(ws[0]);
                });
        DataStream<Tuple2<String, Integer>> dataStream = source
                .assignTimestampsAndWatermarks(watermarkStrategy)
                .map(t -> {
                    logger.info("map on {}", t);
                    return Tuple2.of(t.split(",")[1], 1);
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(t -> {
                    logger.info("key by on {}", t);
                    return t.f0;
                })
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                        .sum(1);
        dataStream.print();

//        dataStream.process(new ProcessFunction<Tuple3<Long, String, Integer>, Object>() {
//            @Override
//            public void processElement(Tuple3<Long, String, Integer> longStringIntegerTuple3, ProcessFunction<Tuple3<Long, String, Integer>, Object>.Context context, Collector<Object> collector) throws Exception {
//                logger.info("find {}", longStringIntegerTuple3);
//            }
//        });
        environment.execute("window wc");
    }
}
