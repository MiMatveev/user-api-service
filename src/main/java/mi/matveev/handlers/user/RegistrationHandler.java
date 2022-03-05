package mi.matveev.handlers.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mi.matveev.database.entitys.User;
import mi.matveev.database.services.ConfirmCodeService;
import mi.matveev.database.services.UserService;
import mi.matveev.enums.BadRequestType;
import mi.matveev.enums.CodeType;
import mi.matveev.exceptions.ValidationError;
import mi.matveev.handlers.RequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationHandler implements RequestHandler<User> {
    private final UserService userService;
    private final ConfirmCodeService actionCodeService;

    @Override
    public ResponseEntity<?> process(User requestBody) {
        try {
            validateFields(requestBody);
            checkValueExists(requestBody);

            String userId = createUser(requestBody);
            createCode(userId);

            return new ResponseEntity<>(userId, HttpStatus.CREATED);
        } catch (ValidationError error) {
            return new ResponseEntity<>(error.getResponseBody(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateFields(User requestBody) throws ValidationError {
        checkFieldPresence(requestBody);
        checkFieldContent(requestBody);
    }

    private void checkFieldPresence(User requestBody) throws ValidationError {
        List<String> missingFields = new ArrayList<>();

        if (requestBody.getLogin() == null || requestBody.getLogin().isEmpty()) {
            missingFields.add("login");
        }
        if (requestBody.getEmail() == null || requestBody.getEmail().isEmpty()) {
            missingFields.add("email");
        }
        if (requestBody.getPassword() == null || requestBody.getPassword().isEmpty()) {
            missingFields.add("password");
        }

        if (!missingFields.isEmpty()) {
            throw new ValidationError(BadRequestType.MISSING_FIELD, missingFields.toString());
        }
    }

    private void checkFieldContent(User requestBody) throws ValidationError {
        String loginRegex = "^[A-Za-z][A-Za-z0-9]{5,29}$";
        String emailRegex = "^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*\\.[A-Za-z]{2,6}$";
        String passwordRegex = "^(.){6,30}$";

        List<String> invalidFields = new ArrayList<>();

        if (!requestBody.getLogin().matches(loginRegex)) {
            invalidFields.add("login");
        }
        if (!requestBody.getEmail().matches(emailRegex)) {
            invalidFields.add("email");
        }
        if (!requestBody.getPassword().matches(passwordRegex)) {
            invalidFields.add("password");
        }

        if (!invalidFields.isEmpty()) {
            throw new ValidationError(BadRequestType.INVALID_FIELD, invalidFields.toString());
        }
    }

    private void checkValueExists(User requestBody) throws ValidationError {
        if (userService.getByLogin(requestBody.getLogin()).isPresent()) {
            throw new ValidationError(BadRequestType.LOGIN_USED);
        }
        if (userService.getByEmail(requestBody.getEmail()).isPresent()) {
            throw new ValidationError(BadRequestType.EMAIL_USED);
        }
    }

    private String createUser(User requestBody) {
        return userService.create(requestBody);
    }

    private void createCode(String userId) {
        LocalDate validTo = LocalDate.now().plusDays(7);
        actionCodeService.create(userId, CodeType.REGISTRATION, 6, validTo);
    }
}
