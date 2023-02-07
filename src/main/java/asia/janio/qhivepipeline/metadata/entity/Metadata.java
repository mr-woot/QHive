package asia.janio.qhivepipeline.metadata.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "metadata")
@Getter
@Setter
@NoArgsConstructor
public class Metadata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "query", nullable = false, columnDefinition = "TEXT")
    private String query;

    @Column(name = "description")
    private String description;

    @Column(name = "scheduleFrequency")
    private String scheduleFrequency;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private Long updatedAt;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now().toEpochMilli();
        this.updatedAt = Instant.now().toEpochMilli();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }

    public Metadata(String name, String query, String description, String scheduleFrequency, String createdBy) {
        this.name = name;
        this.query = query;
        this.description = description;
        this.scheduleFrequency = scheduleFrequency;
        this.createdBy = createdBy;
    }
}
