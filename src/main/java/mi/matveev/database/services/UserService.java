package mi.matveev.database.services;

import mi.matveev.database.entitys.User;

import java.util.Optional;

public interface UserService {
    String create(User user);

    Optional<User> getByLogin(String login);

    Optional<User> getByEmail(String email);

    Optional<User> getByLoginOrEmail(String login, String email);
}
