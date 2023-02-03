package asia.janio.qhivepipeline.kafka;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaConsumer {

    private final String TOPIC_NAME = "hive-janio-test";

    private final String GROUP_ID = "hive-janio-test";

    @KafkaListener(topics = TOPIC_NAME,
                    groupId = GROUP_ID)
    public void consume(CreateQueryPayload data){
        log.info(String.format("Message received -> %s", data.toString()));
    }
}