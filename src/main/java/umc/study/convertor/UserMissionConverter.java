package umc.study.convertor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Mission;
import umc.study.domain.mapping.UserMission;
import umc.study.domain.User;
import umc.study.web.dto.UserDTO.UserResponseDTO;
import umc.study.web.dto.UserDTO.UserRequestDTO;


@Component
@AllArgsConstructor
public class UserMissionConverter {

    // UserMissionResponseDTO를 받아서 UserMission를 확인하는 메소드
    public static UserResponseDTO.JoinUserMissionResultDTO toJoinUserMissionDTO(UserMission userMission) {
        return UserResponseDTO.JoinUserMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    // UserMissionRequestDTO를 UserMission 객체로 바꿔주는 메소드
    public static UserMission toUserMission(User user, Mission mission, UserRequestDTO.JoinUserMissionDTO request) {
        return UserMission.builder()
                .mission(mission)
                .status(request.getStatus())
                .user(user)
                .build();
    }


}
