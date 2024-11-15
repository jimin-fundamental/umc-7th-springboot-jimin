package umc.study.service.UserMissionService;

import umc.study.domain.Review;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.UserMissionDTO.UserMissionRequestDTO;

public interface UserMissionCommandService {
    public UserMission addUserMission(Long missionId, UserMissionRequestDTO.JoinDto request);
}
