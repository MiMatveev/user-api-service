package mi.matveev.database.entitys;

import lombok.Getter;
import lombok.Setter;
import mi.matveev.enums.CodeType;
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
@Table(name = "CODES")
public class Code {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ACTION")
    private CodeType codeType;

    @Column(name = "VALID_TO")
    private LocalDate validTo;
}
