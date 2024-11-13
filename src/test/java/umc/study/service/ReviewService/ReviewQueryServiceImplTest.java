package umc.study.service.ReviewService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.study.domain.Review;
import umc.study.repository.ReviewRepository.ReviewRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewQueryServiceImplTest {

    @Autowired
    private ReviewQueryService reviewQueryService;
    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    void addReview() {
        //Given
        String title = "맨날 시켜먹을래요";
        String content = "이렇게 맛있는 초밥집은 처음이에요. 사장님 최고";
        String score = "TWO";
        Long userId = 3L;
        Long restaurantId = 5L;

        //When
        Review review = reviewQueryService.addReview(title, content, score, userId, restaurantId);

        //Then
        //지금 쓴 리뷰 있는지 체크
        assertNotNull(review);
        assertEquals("맨날 시켜먹을래요", review.getTitle());
        assertEquals("이렇게 맛있는 초밥집은 처음이에요. 사장님 최고", review.getContent());
        assertEquals("TWO", review.getScore().name());
        assertEquals(userId, review.getUser().getId());
        assertEquals(restaurantId, review.getRestaurant().getId());

        // 전체 리뷰 목록 조회 및 출력
        List<Review> allReviews = reviewRepository.findAll();
        System.out.println("===== 전체 리뷰 목록 =====");
        for (Review r : allReviews) {
            System.out.println("Review ID: " + r.getId() +
                    ", Title: " + r.getTitle() +
                    ", Content: " + r.getContent() +
                    ", Score: " + r.getScore() +
                    ", User ID: " + (r.getUser() != null ? r.getUser().getId() : "null") +
                    ", Restaurant ID: " + (r.getRestaurant() != null ? r.getRestaurant().getId() : "null"));
        }


    }



}