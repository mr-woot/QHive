package asia.janio.qhivepipeline.kafka;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {
    @Bean
    ConsumerFactory<String, CreateQueryPayload> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("bootstrap.servers", "b-2.data-pipeline.9pjnsu.c3.kafka.ap-southeast-1.amazonaws.com:9092,b-1.data-pipeline.9pjnsu.c3.kafka.ap-southeast-1.amazonaws.com:9092");
        properties.put("group.id", "hive-janio-test");
        properties.put("enable.auto.commit", true);
        properties.put("auto.commit.interval.ms", "10");
        properties.put("session.timeout.ms", "60000");
        ErrorHandlingDeserializer<String> headerErrorHandlingDeserializer
                = new ErrorHandlingDeserializer<>(new StringDeserializer());
        ErrorHandlingDeserializer<CreateQueryPayload> errorHandlingDeserializer
                = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CreateQueryPayload.class));
        return new DefaultKafkaConsumerFactory<>(properties, headerErrorHandlingDeserializer, errorHandlingDeserializer);
    }

    @Bean
    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(
            ConsumerFactory<String, CreateQueryPayload> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, CreateQueryPayload> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConcurrency(2);
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        kafkaListenerContainerFactory.setErrorHandler(new KafkaErrorHandler());
        return kafkaListenerContainerFactory;
    }
}
