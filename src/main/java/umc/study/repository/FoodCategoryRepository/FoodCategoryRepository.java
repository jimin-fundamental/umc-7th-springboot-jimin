package umc.study.repository.FoodCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.FoodCategory;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long>{
    // 카테고리 이름으로 조회하는 메서드 정의
    Optional<FoodCategory> findByName(String name);

    boolean existsByName(String value);
}
