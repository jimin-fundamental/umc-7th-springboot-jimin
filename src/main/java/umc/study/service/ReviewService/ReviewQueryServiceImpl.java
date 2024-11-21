package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.RestaurantRepository.RestaurantRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional //무언가를 작성해야하므로 readOnly를 작성하지 않음
    @Override
    public Review addReview(String title, String content, String score, Long userId, Long restaurantId) {
        Review newReview = reviewRepository.saveReview(title, content, score, userId, restaurantId);

        // 저장된 리뷰 출력 (디버깅용)
        if (newReview != null) {
            System.out.println("Review saved successfully: " + newReview.toString());
        } else {
            System.out.println("Failed to save the review.");
        }

        return newReview;
    }

}
