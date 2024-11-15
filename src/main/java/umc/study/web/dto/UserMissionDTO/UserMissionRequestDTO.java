package umc.study.web.dto.UserMissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.enums.Status;
import umc.study.validation.annotation.DoingMission;
import umc.study.validation.annotation.ExistId;

public class UserMissionRequestDTO {
    @Getter
    public static class JoinDto{
        @NotNull
        Status status;
        @DoingMission
        Long missionId;
        //스프링 시큐리티 전
        @ExistId
        Long userId;

    }
}
