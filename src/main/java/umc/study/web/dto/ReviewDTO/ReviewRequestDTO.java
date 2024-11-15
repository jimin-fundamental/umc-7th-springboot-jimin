package umc.study.web.dto.ReviewDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.User;
import umc.study.domain.enums.Score;

public class ReviewRequestDTO {
    @Getter
    public static class JoinDto{
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
