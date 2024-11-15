package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;

public interface RestaurantCommandService {
    public String getRestaurantName(Long id);
    public Restaurant addRestaurant(Long regionId, RestaurantRequestDTO.JoinDto request);
}
