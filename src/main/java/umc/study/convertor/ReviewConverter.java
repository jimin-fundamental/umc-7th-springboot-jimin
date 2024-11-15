package umc.study.convertor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

@Component
@AllArgsConstructor
public class ReviewConverter {

    // ReviewResponseDTO를 받아서 Review를 확인하는 메소드
    public static ReviewResponseDTO.JoinResultDTO toJoinResultDTO(Review review) {
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // ReviewRequestDTO를 Review 객체로 바꿔주는 메소드
    public static Review toReview(User user, Restaurant restaurant, ReviewRequestDTO.JoinDto request) {
        return Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .score(request.getScore())
                .user(user)
                .restaurant(restaurant)//path variable에 있는 region-id를 어떻게 꺼내와?
                .build();
    }


}
