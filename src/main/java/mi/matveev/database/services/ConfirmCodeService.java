package mi.matveev.database.services;

import mi.matveev.enums.CodeType;

import java.time.LocalDate;

public interface ConfirmCodeService {
    void create(String userId, CodeType codeType, int codeLength, LocalDate validTo);
}
