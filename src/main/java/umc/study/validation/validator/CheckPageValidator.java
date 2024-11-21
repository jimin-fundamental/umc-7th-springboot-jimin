package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.RestaurantService.RestaurantQueryService;
import umc.study.validation.annotation.CheckPage;

@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    //유저가 입력한 페이지 번호에 더하기 1하기, page 번호가 음수이면 RestControllerAdivce로 에러 발생시키기
//    @Override
//    public boolean isValid(Long pageNumber, ConstraintValidatorContext context) {
//        if (pageNumber == null) {
//            return false; // 페이지 번호가 null인 경우 유효하지 않음
//        }
//
//        // 유저가 입력한 페이지 번호가 음수인 경우
//        if (pageNumber < 0) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_EXIST.toString())
//                    .addConstraintViolation();
//        }
//
//        // 페이지 번호에 1을 더한 값으로 처리
//        return true;
//    }

    @Override
    public boolean isValid(Integer pageNumber, ConstraintValidatorContext context) {
        if (pageNumber == null) {
            return false; // 페이지 번호가 null인 경우 유효하지 않음
        }

        if (pageNumber < 0) {
            throw new IllegalArgumentException("페이지 번호는 음수가 될 수 없습니다.");
        }

        // 페이지 번호가 유효하면 true 반환
        return true;
    }

}
