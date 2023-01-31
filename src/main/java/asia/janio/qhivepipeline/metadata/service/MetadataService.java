package asia.janio.qhivepipeline.metadata.service;

import asia.janio.qhivepipeline.metadata.entity.CreateQueryPayload;
import asia.janio.qhivepipeline.metadata.entity.Metadata;
import asia.janio.qhivepipeline.metadata.repository.MetadataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MetadataService {

    private final MetadataRepository metadataRepository;

    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public ResponseEntity<Metadata> createQuery(CreateQueryPayload queryPayload) {
        String query = queryPayload.getQuery();
        String name = queryPayload.getName();
        String scheduleFrequency = queryPayload.getScheduleFrequency();
        Metadata metadata = new Metadata(name, query, scheduleFrequency);
        metadataRepository.save(metadata);
        return ResponseEntity.status(HttpStatus.CREATED).body(metadata);
    }

    public ResponseEntity<?> listQueries() {
        List<Metadata> list = metadataRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    public ResponseEntity<?> getQuery(String name) {
        Optional<Metadata> metadata = metadataRepository.findByName(name);
        if (metadata.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(metadata.get());
        } else {
            String message = "query with name: " + name + " not found";
            Map<String, Object> result = new HashMap<>();
            result.put("status", HttpStatus.NOT_FOUND);
            result.put("message", message);
            result.put("error", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
