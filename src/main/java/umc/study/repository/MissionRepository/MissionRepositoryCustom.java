package umc.study.repository.MissionRepository;

import umc.study.domain.Mission;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDateTime;
import java.util.List;

public interface MissionRepositoryCustom {
    // 내가 진행 중인, 진행 완료한 미션 모아서 보는 쿼리
    List<UserMission> dynamicQueryWithBooleanBuilder(Long userId,
                                                     String status, LocalDateTime createdAt,
                                                     int pageSize);
    //현재 선택된 지역에서 도전 가능한 미션 목록을 조회하는 쿼리
    List<Mission> dynamicQueryWithBooleanBuilder(Long userId, Long regionId, LocalDateTime createdAt,
                                                 int pageSize);
}
