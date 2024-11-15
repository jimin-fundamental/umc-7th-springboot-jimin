package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.DoingMissionValidator;
import umc.study.validation.validator.IdExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DoingMissionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DoingMission {

    String message() default "해당하는 미션은 이미 실행중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}