package umc.study.service.UserMissionService;

import umc.study.domain.Review;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserDTO.UserRequestDTO;

public interface UserMissionCommandService {
    public UserMission addUserMission(Long missionId, UserRequestDTO.JoinUserMissionDTO request);
}
