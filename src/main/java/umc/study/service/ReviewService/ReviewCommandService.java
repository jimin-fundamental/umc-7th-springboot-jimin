package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;

public interface ReviewCommandService {
    public Review addReview(Long restaurantId, RestaurantRequestDTO.JoinReviewDTO request);
}
