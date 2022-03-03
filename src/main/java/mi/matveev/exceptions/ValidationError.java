package mi.matveev.exceptions;

import lombok.Getter;
import mi.matveev.api.template.BadRequest;
import mi.matveev.enums.BadRequestType;

@Getter
public class ValidationError extends Exception {
    private final BadRequestType error;

    private final String message;

    public ValidationError(BadRequestType errorType) {
        this.error = errorType;
        this.message = errorType.getMessage();
    }

    public ValidationError(BadRequestType errorType, String... params) {
        this.error = errorType;
        this.message = String.format(errorType.getMessage(), (Object[]) params);
    }

    public BadRequest getResponseBody() {
        return new BadRequest(this.error.toString(), this.message);
    }
}
