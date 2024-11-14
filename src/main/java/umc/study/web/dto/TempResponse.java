package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TempResponse {

    @Builder //우리가 만드는 instance이므로, builder가 필요
    @Getter
    @NoArgsConstructor // 매개변수가 없는 기본 생성자 (no-argument constructor) 를 자동으로 생성 - 기본생성자 용도
    @AllArgsConstructor
    public static class TempTestDTO{
        String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }
}