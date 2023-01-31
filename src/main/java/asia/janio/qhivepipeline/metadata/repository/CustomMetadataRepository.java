package asia.janio.qhivepipeline.metadata.repository;

import asia.janio.qhivepipeline.metadata.entity.Metadata;

import java.util.Optional;

public interface CustomMetadataRepository {
    Optional<Metadata> findByName(String name);
}
