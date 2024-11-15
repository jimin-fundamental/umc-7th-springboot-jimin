package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.RestaurantConverter;
import umc.study.domain.Restaurant;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/{region-id}") //회원가입
    public ApiResponse<RestaurantResponseDTO.JoinResultDTO> addRestaurant(
            @PathVariable(value = "region-id") Long regionId,
            @RequestBody @Valid RestaurantRequestDTO.JoinDto request){
        Restaurant restaurant = restaurantCommandService.addRestaurant(regionId, request);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }
}