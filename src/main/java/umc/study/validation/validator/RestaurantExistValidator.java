package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.service.UserService.UserCommandService;
import umc.study.validation.annotation.ExistId;
import umc.study.validation.annotation.ExistRestaurant;

import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
public class RestaurantExistValidator implements ConstraintValidator<ExistRestaurant, Long> {

    private final RestaurantCommandService restaurantService;

    @Override
    public void initialize(ExistRestaurant constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 값이 null이거나 빈 문자열인 경우 유효한 것으로 간주
        if (value == null) {
            return true;
        }

        // 입력된 카테고리가 존재하는지 확인
        boolean isValid = restaurantService.existsById(value);

        // 존재하지 않는 경우, 커스텀 메시지 설정
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.RESTAURANT_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;

    }
}