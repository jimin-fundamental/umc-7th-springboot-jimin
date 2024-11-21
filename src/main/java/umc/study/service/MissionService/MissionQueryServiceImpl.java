package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.enums.Status;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public List<Mission> getAllMission() {
        return missionRepository.findAll();
    }

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }


    @Override
    public List<UserMission> findUserMissionsByCriteria(Long userId, String status, LocalDateTime createdAt, int pageSize) {
        // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
        List<UserMission> filteredMissions = missionRepository.dynamicQueryWithBooleanBuilder(userId, status, createdAt, pageSize);

        // Mission의 name을 출력하기 위해 지연 로딩 문제를 방지하는 추가 코드
        filteredMissions.forEach(userMission -> {
            if (userMission.getMission() != null) {
                System.out.println("UserMission ID: " + userMission.getId() +
                        ", Mission Name: " + userMission.getMission().getName());
            }
        });

        return filteredMissions;
    }

    //해당 region에 있는 전체 mission 중 이 userid에 대해 user_mission table에 있지 않은 미션을 조회
    @Override
    public List<Mission> findUserMissionsByRegionCriteria(Long userId, Long regionId, LocalDateTime createdAt, int pageSize) {
        // 동적 쿼리를 사용하여 UserMission 목록 조회
        List<Mission> filteredRegionMissions = missionRepository.dynamicQueryWithBooleanBuilder(userId, regionId, createdAt, pageSize);

        // 조회된 미션이 있는지 확인 후 출력
        if (!filteredRegionMissions.isEmpty()) {
            System.out.println("Region " + filteredRegionMissions.get(0).getRegion().getRegionName() + "에서 가능한 미션들은:");
            filteredRegionMissions.forEach(mission -> {
                System.out.println("Mission Name: " + mission.getName());
            });
        } else {
            System.out.println("해당 지역에서 수행 가능한 미션이 없습니다.");
        }

        return filteredRegionMissions;
    }


    @Override
    public Page<Mission> getMissionList(Long userId, Integer adjustedPage) {
        User user = userRepository.findById(userId).get();
        Page<Mission> UserPage = missionRepository.findAllByUser(user, PageRequest.of(adjustedPage, 10));
        return UserPage;
    }

    @Override
    public Page<UserMission> getUserMissionList(Long userId, Status status, Integer adjustedPage) {
        User user = userRepository.findById(userId).get();
        Page<UserMission> UserPage = userMissionRepository.findAllByUserAndStatus(user, status, PageRequest.of(adjustedPage, 10));
        return UserPage;
    }

}
