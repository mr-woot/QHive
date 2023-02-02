package asia.janio.qhivepipeline.hive.controller;

import asia.janio.qhivepipeline.hive.service.HiveServiceImpl;
import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiveController {
    @Autowired
    private HiveServiceImpl hiveService;

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
        hiveService.select(queryPayload.getQuery());
        return ResponseEntity.status(HttpStatus.CREATED).body(queryPayload);
    }
}
