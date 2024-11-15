package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.convertor.RestaurantConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.enums.RegionName;
import umc.study.repository.RegionRepository.RegionRepository;
import umc.study.repository.RestaurantRepository.RestaurantRepository;
import umc.study.service.RegionService.RegionService;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService{
    private final RestaurantRepository restaurantRepository;
    private final RegionRepository regionRepository;
    private final RegionService regionService;

    @Override
    public String getRestaurantName(Long id) {
        String name = restaurantRepository.getRestaurantName(id);
        return name;
    }

    @Override
    public Restaurant addRestaurant(Long regionId, RestaurantRequestDTO.JoinDto request) {
        //regionId로 region 만들기
        // regionId로 region 검색
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorStatus.REGION_NOT_FOUND.toString()));

        // Restaurant 생성
        Restaurant newStore = RestaurantConverter.toRestaurant(region, request);


        return restaurantRepository.save(newStore);
    }
}
