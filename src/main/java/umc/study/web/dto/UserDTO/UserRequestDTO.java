package umc.study.web.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.enums.Gender;
import umc.study.validation.annotation.ExistCategories;

import java.time.LocalDate;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        @NotBlank
        String username;
        @NotNull
        Gender gender;
        @NotNull
        LocalDate birthdate;
        @Size(min = 2, max = 1024)
        String address;
        @ExistCategories
        String foodCategoryName; // 카테고리 이름으로 변경
    }
}
