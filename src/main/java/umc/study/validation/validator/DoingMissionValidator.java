package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.service.UserService.UserCommandService;
import umc.study.validation.annotation.DoingMission;
import umc.study.validation.annotation.ExistId;

@Component
@RequiredArgsConstructor
public class DoingMissionValidator implements ConstraintValidator<DoingMission, Long> {

    private final UserMissionRepository userMissionRepository;
    @Override
    public void initialize(DoingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 값이 null이거나 빈 문자열인 경우 유효한 것으로 간주
        if (value == null) {
            return true;
        }

        // 이 미션이 진행 중인지 파악
        boolean isValid = userMissionRepository.existsById(value);

        // 존재하지 않는 경우, 커스텀 메시지 설정
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;

    }
}
