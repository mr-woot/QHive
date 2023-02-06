package asia.janio.qhivepipeline.kafka;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Log4j2
public class KafkaProducer {

    @Value("${spring.kafka.template.default-topic}")
    private String TOPIC_NAME;

    private final KafkaTemplate<String, CreateQueryPayload> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, CreateQueryPayload> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendMessage(CreateQueryPayload data){
        log.info(String.format("Message sent -> %s", data.toString()));

        Message<CreateQueryPayload> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                .build();

        kafkaTemplate.send(message);
        return true;
    }
}