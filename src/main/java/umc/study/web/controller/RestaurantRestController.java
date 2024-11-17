package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.RestaurantConverter;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.service.RestaurantService.RestaurantQueryService;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.validation.annotation.ExistRestaurant;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;
    private final ReviewCommandService reviewCommandService;
    private final RestaurantQueryService restaurantQueryService;


    @PostMapping("/{regionId}") //회원가입
    public ApiResponse<RestaurantResponseDTO.JoinRestaurantResultDTO> addRestaurant(
            @PathVariable(value = "regionId") Long regionId,
            @RequestBody @Valid RestaurantRequestDTO.JoinRestaurantDTO request){
        Restaurant restaurant = restaurantCommandService.addRestaurant(regionId, request);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }

    //가게의 리뷰 추가
    @PostMapping("/{restaurantId}/review") //회원가입
    public ApiResponse<RestaurantResponseDTO.JoinReviewResultDTO> addReview(
            @PathVariable(value = "restaurantId") Long restaurantId,
            @RequestBody @Valid RestaurantRequestDTO.JoinReviewDTO request){
        //@RequestHeader("Authorization") String accessToken) 나중에 추가
        Review review = reviewCommandService.addReview(restaurantId, request);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinReviewResultDTO(review));
    }

    //가게의 리뷰 조회
    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<RestaurantResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList= restaurantQueryService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(RestaurantConverter.reviewPreViewListDTO(reviewList));
    }
}