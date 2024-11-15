package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review addReview(Long restaurantId, ReviewRequestDTO.JoinDto request);
}
