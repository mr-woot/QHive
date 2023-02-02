package asia.janio.qhivepipeline;

import asia.janio.qhivepipeline.hive.config.HiveConnection;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Log4j2
public class QHivePipelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(QHivePipelineApplication.class, args);
//        HiveService.run();
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        log.info("CLOSING HIVE CONNECTION...");
        HiveConnection.getInstance().getConnection().close();
        log.info("HIVE CONNECTION CLOSED");
    }

}
