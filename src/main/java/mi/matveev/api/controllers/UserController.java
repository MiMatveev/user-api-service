package mi.matveev.api.controllers;

import lombok.RequiredArgsConstructor;
import mi.matveev.database.entitys.User;
import mi.matveev.handler.RequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final RequestHandler<User> registrationHandler;

    @PostMapping(value = "/auth/user")
    public ResponseEntity<?> registration(@RequestBody User requestBody) {
        return registrationHandler.process(requestBody);
    }
}
