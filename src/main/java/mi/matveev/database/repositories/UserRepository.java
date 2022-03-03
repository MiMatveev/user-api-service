package mi.matveev.database.repositories;

import mi.matveev.database.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String login, String email);
}
