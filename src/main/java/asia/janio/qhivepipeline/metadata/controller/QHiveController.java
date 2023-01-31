package asia.janio.qhivepipeline.metadata.controller;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import asia.janio.qhivepipeline.metadata.service.MetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class QHiveController {

    private final MetadataService metadataService;

    public QHiveController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @PostMapping
    public ResponseEntity<?> createQuery(@RequestBody CreateQueryPayload queryPayload) {
        return metadataService.createQuery(queryPayload);
    }

    @GetMapping
    public ResponseEntity<?> listQueries() {
        return metadataService.listQueries();
    }
}
