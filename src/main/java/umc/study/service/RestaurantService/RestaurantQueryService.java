package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findRestaurant(Long id); // 단일 객체를 반환 - null일 수 있으므로 optional로 감싸줘야 함
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float score); // List 자체를 반환하기에, list가 비어있어도 오류가 발생하지 않음
}
