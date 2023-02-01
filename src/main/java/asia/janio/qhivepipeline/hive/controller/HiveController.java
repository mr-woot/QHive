package asia.janio.qhivepipeline.hive.controller;

import asia.janio.qhivepipeline.hive.service.HiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hive")
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
}
