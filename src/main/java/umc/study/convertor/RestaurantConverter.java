package umc.study.convertor;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.*;
import umc.study.domain.enums.RegionName;
import umc.study.web.dto.RestaurantDTO.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestaurantConverter {

    // RestaurantResponseDTO를 받아서 Restaurant를 확인하는 메소드
    public static RestaurantResponseDTO.JoinRestaurantResultDTO toJoinResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.JoinRestaurantResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    // RestaurantRequestDTO를 Restaurant 객체로 바꿔주는 메소드
    public static Restaurant toRestaurant(Region region, RestaurantRequestDTO.JoinRestaurantDTO request) {

        return Restaurant.builder()
                .name(request.getName())
                .ownerId(request.getOwnerId())
                .region(region)//path variable에 있는 region-id를 어떻게 꺼내와?
                .build();
    }

    //REVIEW
    // ReviewResponseDTO를 받아서 Review를 확인하는 메소드
    public static RestaurantResponseDTO.JoinReviewResultDTO toJoinReviewResultDTO(Review review) {
        return RestaurantResponseDTO.JoinReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // ReviewRequestDTO를 Review 객체로 바꿔주는 메소드
    public static Review toReview(User user, Restaurant restaurant, RestaurantRequestDTO.JoinReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .score(request.getScore())
                .user(user)
                .restaurant(restaurant)//path variable에 있는 region-id를 어떻게 꺼내와?
                .build();
    }

    //가게의 리뷰 목록의 각 리뷰 DTO
    public static RestaurantResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return RestaurantResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getUsername()) //객체그래프탐색 - ManyToOne으로 지정한 Member 통해 가져오기
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }
                //가게의 리뷰 목록 조회
            //    public static class ReviewPreViewListDTO {
            //        List<RestaurantResponseDTO.ReviewPreViewDTO> reviewList;
            //        Integer listSize;
            //        Integer totalPage;
            //        Long totalElements;
            //        Boolean isFirst;
            //        Boolean isLast;
            //    }
    public static RestaurantResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<RestaurantResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(RestaurantConverter::reviewPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}



