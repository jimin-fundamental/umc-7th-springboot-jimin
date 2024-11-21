package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.CheckPageValidator;
import umc.study.validation.validator.RestaurantExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "입력된 페이지가 정상적인 입력이 아닙니다.(1 이상의 양수를 입력해주세요).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
