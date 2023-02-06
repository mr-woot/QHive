package asia.janio.qhivepipeline.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private String error;
    private HttpStatus status;
    private Object data;
}
