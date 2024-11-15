package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.CategoriesExistValidator;
import umc.study.validation.validator.IdExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {

    String message() default "해당하는 유저의 id가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}