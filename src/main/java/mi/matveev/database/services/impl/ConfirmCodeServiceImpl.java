package mi.matveev.database.services.impl;

import lombok.RequiredArgsConstructor;
import mi.matveev.database.entitys.ConfirmCode;
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
        ConfirmCode actionCode = new ConfirmCode();
        String codeId = UUID.randomUUID().toString();

        actionCode.setId(codeId);
        actionCode.setUserId(userId);
        actionCode.setCode(Generator.generateString(codeLength));
        actionCode.setCodeType(codeType);
        actionCode.setValidTo(validTo);

        actionCodeRepository.save(actionCode);
    }
}
