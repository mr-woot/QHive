package asia.janio.qhivepipeline.metadata.entity;

import lombok.Data;

@Data
public class CreateQueryPayload {

    private String name;
    private String query;
    private String description;
    private String scheduleFrequency;
    private String createdBy;
}
