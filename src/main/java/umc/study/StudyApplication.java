package umc.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.study.service.RestaurantService.RestaurantQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(umc.study.StudyApplication.class, args);
    }
}
//    @Bean
//    public CommandLineRunner run(ApplicationContext context) {
//        return args -> {
//            RestaurantQueryService restaurantService = context.getBean(RestaurantQueryService.class);
//
//            // 파라미터 값 설정
//            String name = "요아정";
//            Float score = 4.0f;
//
//            // 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//            System.out.println("Executing findRestaurantsByNameAndScore with parameters:");
//            System.out.println("Name: " + name);
//            System.out.println("Score: " + score);
//
//            restaurantService.findRestaurantsByNameAndScore(name, score)
//                    .forEach(System.out::println);
//        };
//
//
//}}
