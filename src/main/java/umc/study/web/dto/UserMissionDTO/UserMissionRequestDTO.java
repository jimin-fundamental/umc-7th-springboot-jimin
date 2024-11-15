package umc.study.web.dto.UserMissionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.enums.Score;
import umc.study.domain.enums.Status;

public class UserMissionRequestDTO {
    @Getter
    public static class JoinDto{
        @NotNull
        Status status;
        @NotNull
        Long missionId;
        //스프링 시큐리티 전
        Long userId;

    }
}
