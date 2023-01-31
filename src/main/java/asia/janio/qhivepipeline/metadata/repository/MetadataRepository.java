package asia.janio.qhivepipeline.metadata.repository;

import asia.janio.qhivepipeline.metadata.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long>, CustomMetadataRepository {
}
