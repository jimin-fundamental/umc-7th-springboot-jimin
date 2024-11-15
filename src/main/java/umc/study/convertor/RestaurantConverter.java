package umc.study.convertor;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.FoodCategory;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.enums.RegionName;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;

@Component
@AllArgsConstructor
public class RestaurantConverter {

    // RestaurantResponseDTO를 받아서 Restaurant를 확인하는 메소드
    public static RestaurantResponseDTO.JoinResultDTO toJoinResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.JoinResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    // RestaurantRequestDTO를 Restaurant 객체로 바꿔주는 메소드
    public static Restaurant toRestaurant(Region region, RestaurantRequestDTO.JoinDto request) {

        return Restaurant.builder()
                .name(request.getName())
                .ownerId(request.getOwnerId())
                .region(region)//path variable에 있는 region-id를 어떻게 꺼내와?
                .build();
    }


}
