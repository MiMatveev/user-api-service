package mi.matveev.database.entitys;

import lombok.Getter;
import lombok.Setter;
import mi.matveev.enums.UserRole;
import mi.matveev.enums.UserStatus;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Component
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private UserStatus status;

    @Column(name = "ROLE")
    private UserRole role;

    @Column(name = "DATA_MODIF")
    private LocalDate dataModif;
}
