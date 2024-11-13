package umc.study.repository.ReviewRepository;

import umc.study.domain.Review;

public interface ReviewRepositoryCustom {
    // 데이터베이스에 리뷰 새로 추가
    Review saveReview(String title, String content, String score, Long userId, Long restaurantId);

}
