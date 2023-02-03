package asia.janio.qhivepipeline.hive.controller;

import asia.janio.qhivepipeline.common.ApiResponse;
import asia.janio.qhivepipeline.hive.service.HiveServiceImpl;
import asia.janio.qhivepipeline.kafka.KafkaProducer;
import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiveController {

    private final KafkaProducer kafkaProducer;

    private final HiveServiceImpl hiveService;

    public HiveController(HiveServiceImpl hiveService, KafkaProducer kafkaProducer) {
        this.hiveService = hiveService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/show-tables")
    public ResponseEntity<?> showTables() {
        return ResponseEntity.status(HttpStatus.OK).body(hiveService.listAllTables());
    }

    @GetMapping("/describe-table")
    public ResponseEntity<?> describeTable(@RequestParam String tableName) {
        return ResponseEntity.status(HttpStatus.OK).body(hiveService.describeTable(tableName));
    }

    @PostMapping("/executeHql")
    public ResponseEntity<?> executeHql(@RequestBody CreateQueryPayload queryPayload) {
        boolean res = kafkaProducer.sendMessage(queryPayload);
        if (res) {
//        hiveService.select(queryPayload.getQuery());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.builder()
                        .data(queryPayload)
                        .message("Query scheduled successfully")
                        .error(null)
                        .status(HttpStatus.CREATED)
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponse.builder()
                        .data(null)
                        .message("Error scheduling the query")
                        .error("Error scheduling the query")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
            );
        }
    }
}
