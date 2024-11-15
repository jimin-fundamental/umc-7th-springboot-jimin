package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.convertor.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.mapping.UserMission;
import umc.study.domain.User;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.RestaurantRepository.RestaurantRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.UserMissionDTO.UserMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class UserMissionCommandServiceImpl implements UserMissionCommandService {
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;


    @Override
    public UserMission addUserMission(Long missionId, UserMissionRequestDTO.JoinDto request) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.MISSION_NOT_FOUND.toString()));
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.USER_NOT_FOUND.toString()));

        // UserMission 생성
        UserMission newUserMission = UserMissionConverter.toUserMission(user, mission, request);

        return userMissionRepository.save(newUserMission);
    }

}
