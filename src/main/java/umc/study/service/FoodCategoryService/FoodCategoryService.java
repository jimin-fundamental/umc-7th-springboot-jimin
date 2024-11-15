package umc.study.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    // 카테고리 ID 존재 여부 확인 메서드
    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }

    // 카테고리 이름 존재 여부 확인 메서드
    public boolean existsByName(String name) {
        return foodCategoryRepository.existsByName(name);
    }
}