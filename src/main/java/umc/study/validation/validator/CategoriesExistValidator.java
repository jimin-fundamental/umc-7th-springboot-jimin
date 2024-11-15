package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.service.FoodCategoryService.FoodCategoryService;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, String> {

    //private final FoodCategoryRepository foodCategoryRepository;
    private final FoodCategoryService foodCategoryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 값이 null이거나 빈 문자열인 경우 유효한 것으로 간주
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        // 입력된 카테고리가 존재하는지 확인
        boolean isValid = foodCategoryService.existsByName(value);

        // 존재하지 않는 경우, 커스텀 메시지 설정
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;

    }
}
