package mi.matveev.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BadRequestType {
    MISSING_FIELD("Missing a required field: %s"),
    INVALID_FIELD("Field does not match the requirement: %s"),
    LOGIN_USED("Login busy"),
    EMAIL_USED("Email busy");

    private final String message;
}
