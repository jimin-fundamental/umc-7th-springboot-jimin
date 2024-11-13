package umc.study.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {
    //JPAQueryFactory를 각 리포지토리 클래스마다 정의하는 대신,
    // Spring에서 @Bean으로 등록하여 여러 리포지토리 클래스에서 주입받아 사용하는 것
    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}