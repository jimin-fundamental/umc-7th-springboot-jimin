package umc.study.service.ReviewService;


import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface ReviewQueryService {
    Review addReview(String title, String content, String score, Long userId, Long restaurantId);

}
