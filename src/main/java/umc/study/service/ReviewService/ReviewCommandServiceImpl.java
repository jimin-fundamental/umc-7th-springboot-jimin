package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.convertor.ReviewConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.repository.RegionRepository.RegionRepository;
import umc.study.repository.RestaurantRepository.RestaurantRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.service.RegionService.RegionService;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;


    @Override
    public Review addReview(Long restaurantId, ReviewRequestDTO.JoinDto request) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.REGION_NOT_FOUND.toString()));
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.USER_NOT_FOUND.toString()));

        // Review 생성
        Review newReview = ReviewConverter.toReview(user, restaurant, request);


        return reviewRepository.save(newReview);
    }
}
