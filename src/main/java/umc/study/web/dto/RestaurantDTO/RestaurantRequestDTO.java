package umc.study.web.dto.RestaurantDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.Score;
import umc.study.validation.annotation.ExistCategories;

import java.time.LocalDate;

public class RestaurantRequestDTO {

    @Getter
    public static class JoinRestaurantDTO{
        @NotBlank
        String name;
        @NotNull
        Long ownerId;

    }

    @Getter
    public static class JoinReviewDTO{
        @NotBlank
        String title;
        @NotBlank
        String content;
        @NotNull
        Score score;
        //스프링 시큐리티 전
        Long userId;

    }
}
