package asia.janio.qhivepipeline.common;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ApiResponse {
    private String message;
    private String error;
    private HttpStatus status;
    private Object data;
}
