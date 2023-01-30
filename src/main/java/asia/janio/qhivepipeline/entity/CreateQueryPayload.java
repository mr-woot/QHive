package asia.janio.qhivepipeline.entity;

import lombok.Data;

@Data
public class CreateQueryPayload {

    private String name;
    private String query;
    private String description;
    private String scheduleFrequency;
}
