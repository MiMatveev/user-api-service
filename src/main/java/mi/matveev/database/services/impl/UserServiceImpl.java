package mi.matveev.database.services.impl;

import lombok.RequiredArgsConstructor;
import mi.matveev.database.entitys.User;
import mi.matveev.database.repositories.UserRepository;
import mi.matveev.database.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String create(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);

        userRepository.save(user);

        return userId;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email);
    }
}
