package mi.matveev.database.repositories;

import mi.matveev.database.entitys.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmCodeRepository extends JpaRepository<Code, String> {
}
