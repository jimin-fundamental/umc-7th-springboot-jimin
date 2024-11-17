package umc.study.service.RestaurantService;

import umc.study.domain.Restaurant;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;

//데이터의 생성, 수정, 삭제와 같은 변경 작업 담당
public interface RestaurantCommandService {
    //변경
    public String getRestaurantName(Long id);
    //생성
    public Restaurant addRestaurant(Long regionId, RestaurantRequestDTO.JoinRestaurantDTO request);
    //변경, 생성 시 유효성 검사
    boolean existsById(Long id);
}
