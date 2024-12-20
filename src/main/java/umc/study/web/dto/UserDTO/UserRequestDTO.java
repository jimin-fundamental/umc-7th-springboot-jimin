package umc.study.web.dto.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.Role;
import umc.study.domain.enums.Status;
import umc.study.validation.annotation.DoingMission;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistId;

import java.time.LocalDate;

public class UserRequestDTO {

    @Getter
    @Setter
    public static class JoinUserDTO{
        @NotBlank
        String username;
        @NotBlank
        @Email
        String email;    // 이메일 필드 추가

        @NotNull
        String password;    // 비밀번호 필드 추가

        @NotNull
        Gender gender;
        @NotNull
        LocalDate birthdate;
        @Size(min = 2, max = 1024)
        String address;
        @ExistCategories
        String foodCategoryName; // 카테고리 이름으로 변경
        @NotNull
        Role role;    // 역할 필드 추가
    }

    @Getter
    public static class JoinUserMissionDTO{
        @NotNull
        Status status;
        @DoingMission
        Long missionId;
        //스프링 시큐리티 전
        @ExistId
        Long userId;

    }
}
