package asia.janio.qhivepipeline.metadata.controller;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import asia.janio.qhivepipeline.metadata.service.MetadataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QHiveController {

    private final MetadataService metadataService;

    public QHiveController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @PostMapping(value = "/create-query", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuery(@RequestBody CreateQueryPayload queryPayload) {
        return metadataService.createQuery(queryPayload);
    }

    @GetMapping("/list-queries")
    public ResponseEntity<?> listQueries() {
        return metadataService.listQueries();
    }
}
