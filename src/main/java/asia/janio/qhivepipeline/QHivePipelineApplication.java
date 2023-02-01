package asia.janio.qhivepipeline;

import asia.janio.qhivepipeline.hive.service.HiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QHivePipelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(QHivePipelineApplication.class, args);
        HiveService.run();
    }

}
