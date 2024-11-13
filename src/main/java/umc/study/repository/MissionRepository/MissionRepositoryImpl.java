package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Mission;
import umc.study.domain.QMission;
import umc.study.domain.QRegion;
import umc.study.domain.enums.Status;
import umc.study.domain.mapping.QUserMission;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor // final 필드를 위한 생성자를 자동으로 생성하고, Spring이 JPAQueryFactory를 생성자 주입으로 전달
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QUserMission userMission = QUserMission.userMission;
    private final QRegion region = QRegion.region;

    // Enum 변환을 위한 헬퍼 메서드 추가
    private Status parseStatus(String status) {
        if (status != null && !status.isEmpty()) {
            try {
                return Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value: " + status);
            }
        }
        return null;
    }

    // 공통된 Predicate 생성 메서드
    private BooleanBuilder buildPredicate(Long userId, String status, Long regionId, LocalDateTime createdAt) {
        BooleanBuilder predicate = new BooleanBuilder();


        Status enumStatus = parseStatus(status);
        if (enumStatus != null) {
            predicate.and(userMission.status.eq(enumStatus));
        }

        if (regionId != null) {
            predicate.and(userMission.mission.region.id.eq(regionId));
        }

        if (createdAt != null) {
            predicate.and(userMission.createdAt.lt(createdAt)); // createdAt 기준 이전 데이터 가져오기
        }

        return predicate;
    }

    @Override
    public List<UserMission> dynamicQueryWithBooleanBuilder(Long userId, String status, LocalDateTime createdAt, int pageSize) {
        BooleanBuilder predicate = buildPredicate(userId, status, null, createdAt);

        if (userId != null) {
            predicate.and(userMission.user.id.eq(userId));
        }

        return queryFactory
                .selectFrom(userMission)
                .join(userMission.mission, mission).fetchJoin()
                .where(predicate)
                .orderBy(userMission.mission.createdAt.desc())
                .limit(pageSize)
                .fetch();
    }

    //해당 region에 있는 전체 mission 중 이 userid에 대해 user_mission table에 있지 않은 미션을 조회
    @Override
    public List<Mission> dynamicQueryWithBooleanBuilder(Long userId, Long regionId, LocalDateTime createdAt, int pageSize) {
        BooleanBuilder predicate = buildPredicate(userId, null, regionId, createdAt);

        // 특정 사용자가 수행하지 않은 미션을 찾기 위해 LEFT JOIN 사용
        return queryFactory
                .selectFrom(mission)
                .leftJoin(mission.region, region).fetchJoin()
                .leftJoin(userMission).on(userMission.mission.eq(mission).and(userMission.user.id.eq(userId)))
                .where(userMission.id.isNull()) // userMission에 없는 미션만 조회
                .where(predicate)
                .orderBy(mission.createdAt.desc())
                .limit(pageSize)
                .fetch();
    }


}
