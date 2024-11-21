package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.enums.Status;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
    // 모든 미션 가져오기
    List<Mission> getAllMission();
    //특정 미션 찾기
    Optional<Mission> findMission(Long id);

    // 동적 조건으로 UserMission 조회 (페이징 포함)
    List<UserMission> findUserMissionsByCriteria(Long userId, String status, LocalDateTime createdAt, int pageSize);

    //현재 선택된 지역에서 도전 가능한 미션 목록을 조회하는 쿼리 (페이징 포함)
    public List<Mission> findUserMissionsByRegionCriteria(Long userId, Long regionId, LocalDateTime createdAt, int pageSize);

    Page<Mission> getMissionList(Long userId, Integer adjustedPage);
    Page<UserMission> getUserMissionList(Long userId, Status status, Integer adjustedPage);
}
