package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findRestaurant(Long id);
    List<Restaurant> findRestaurantsByNameAndScore(String name, Float score);
}
