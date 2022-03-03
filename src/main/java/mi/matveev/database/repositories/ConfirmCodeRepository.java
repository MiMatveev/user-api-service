package mi.matveev.database.repositories;

import mi.matveev.database.entitys.ConfirmCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmCodeRepository extends JpaRepository<ConfirmCode, String> {
}
