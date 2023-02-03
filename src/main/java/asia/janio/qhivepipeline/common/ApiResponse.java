package asia.janio.qhivepipeline.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class ApiResponse {
    private String message;
    private String error;
    private HttpStatus status;
    private Object data;
}
