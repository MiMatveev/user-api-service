package mi.matveev.api.template;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequest {
    private String error;
    private String message;
}
