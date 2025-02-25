package qiniu.cdn;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Precal {
    public static final String bootStrapServer = "jjh645:19092";
    public static final String sourceTopic = "cdnlog-raw-0";
    public static final String sourceGroup = "douyin-cal";
    public static final String sinkTopic = "impl_pre_cal";
    public static void main(String[] args) throws Exception {
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(bootStrapServer)
                .setTopics(sourceTopic)
                .setGroupId(sourceGroup)
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();

        KafkaSink<String> sink = KafkaSink.<String>builder()
                .setBootstrapServers(bootStrapServer)
                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
                        .setTopic(sinkTopic)
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build()
                ).setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();

        StreamExecutionEnvironment environment = new StreamExecutionEnvironment();
        DataStreamSource<String> stream = environment.fromSource(source, WatermarkStrategy.noWatermarks(),"");
        stream.map(s -> s.length()).print();

        environment.execute();


    }
}
