package umc.study.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.enums.Score;

import java.util.Locale;

@Repository
@RequiredArgsConstructor //이걸 사용해서 JPAQueryFactory 주입받음 -> entityManager이 내부에 있음
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory; //QueryDSLConfig 에서 entitymanager 이미 정의했지만, 직접적으로 EntityManager를 노출하는 메서드를 제공하지 않습니다.
    private final EntityManager entityManager; // EntityManager 추가

    //얘네들을 검색해서 넣어야 하므로 review가 아닌 restaurant과 user에 대한 Qquery를 넣어줌
    private final QRestaurant restaurant = QRestaurant.restaurant;
    private final QUser user = QUser.user;

    @Override
    public Review saveReview(String title, String content, String score, Long userId, Long restaurantId) {
        //1. 유저 조회
        User existingUser = jpaQueryFactory
                .selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();

        //2. 레스토랑 조회
        Restaurant existingRestaurant = jpaQueryFactory
                .selectFrom(restaurant)
                .where(restaurant.id.eq(restaurantId))
                .fetchOne();

        //null 예외 처리
        if (existingRestaurant == null || existingUser == null) {
            throw new IllegalArgumentException("Invalid userId or restaurantId");
        }

        //string score을 enum으로 바꿔주기
        Score enumScore = null;
        try {
            if (score != null && !score.isEmpty()) {
                enumScore = Score.valueOf(score.toUpperCase()); // 대소문자 무시하고 변환
            } else {
                throw new IllegalArgumentException("Score cannot be null or empty");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid score value: " + score);
        }

        //3. Review 객체 생성
        Review review = Review.builder()
                .title(title)
                .content(content)
                .score(enumScore)
                .user(existingUser)
                .restaurant(existingRestaurant)
                .build();

        //4. JPA 사용해서 Review 저장
        entityManager.persist(review); //JPA에서 새로운 엔티티를 데이터베이스에 저장할 때 사용하는 메서드
        // 만약에, spring data jpa를 사용하면  return reviewRepository.save(review); 이렇게 'save' 바로 사용 가능
        return review;
    }
}
