package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{restaurant-id}") //회원가입
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> addReview(
            @PathVariable(value = "restaurant-id") Long restaurantId,
            @RequestBody @Valid ReviewRequestDTO.JoinDto request){
        //@RequestHeader("Authorization") String accessToken) 나중에 추가
        Review review = reviewCommandService.addReview(restaurantId, request);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }
}