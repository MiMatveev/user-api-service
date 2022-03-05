package mi.matveev.database.services.impl;

import lombok.RequiredArgsConstructor;
import mi.matveev.database.entitys.Code;
import mi.matveev.database.repositories.ConfirmCodeRepository;
import mi.matveev.database.services.ConfirmCodeService;
import mi.matveev.enums.CodeType;
import mi.matveev.utils.Generator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class ConfirmCodeServiceImpl implements ConfirmCodeService {
    private final ConfirmCodeRepository actionCodeRepository;

    @Override
    public void create(String userId, CodeType codeType, int codeLength, LocalDate validTo) {
        Code actionCode = new Code();
        String codeId = UUID.randomUUID().toString();

        actionCode.setId(codeId);
        actionCode.setUserId(userId);
        actionCode.setCode(Generator.generateString(codeLength));
        actionCode.setCodeType(codeType);
        actionCode.setValidTo(validTo);

        actionCodeRepository.save(actionCode);
    }
}
