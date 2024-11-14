package umc.study.repository.UserRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QPoint;
import umc.study.domain.QUser;
import umc.study.domain.UserPointDTO;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QUser user = QUser.user;
    private final QPoint point = QPoint.point;

    //특정 사용자의 총 포인트를 포함하여 유저 정보(이름)를 조회하는 쿼리
    @Override
    public UserPointDTO dynamicQueryWithBooleanBuilder(Long userId) {
        //point table의 amount을 모두 더해서 총포인트
        BooleanBuilder predicate = new BooleanBuilder();

        if (userId != null) {
            predicate.and(user.id.eq(userId));
        }

        // User와 Point를 조인하여 총 포인트를 조회
        return queryFactory
                .select(Projections.constructor(UserPointDTO.class,
                        user.id,
                        user.username,
                        point.amount.sum().coalesce(0) // null 값일 경우 0으로 대체
                ))
                .from(user)
                .leftJoin(point).on(point.user.id.eq(user.id))
                .where(predicate)
                .groupBy(user.id)
                .fetchOne();

        }
}




