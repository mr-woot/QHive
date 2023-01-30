package asia.janio.qhivepipeline.repository;

import asia.janio.qhivepipeline.entity.Metadata;

import java.util.Optional;

public interface CustomMetadataRepository {
    Optional<Metadata> findByName(String name);
}
