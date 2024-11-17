package umc.study.service.RestaurantService;

import org.springframework.data.domain.Page;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;

import java.util.List;
import java.util.Optional;

//데이터 조회, 반환 등의 조회 작업담당
public interface RestaurantQueryService {
    //특정 ID로 식당을 조회
    Optional<Restaurant> findRestaurant(Long id); // 단일 객체를 반환 - null일 수 있으므로 optional로 감싸줘야 함
    //이름과 평점으로 식당 리스트를 조회
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float score); // List 자체를 반환하기에, list가 비어있어도 오류가 발생하지 않음

    Page<Review> getReviewList(Long restaurantId, Integer page);
}
