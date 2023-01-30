package asia.janio.qhivepipeline.repository;

import asia.janio.qhivepipeline.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long>, CustomMetadataRepository {
}
