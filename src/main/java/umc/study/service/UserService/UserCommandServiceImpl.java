package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.convertor.UserConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.User;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserDTO.UserRequestDTO;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {
        // 요청된 카테고리 이름을 사용해 FoodCategory 조회 또는 생성 - Stream 사용
        FoodCategory foodCategory = foodCategoryRepository.findAll().stream()
                .filter(category -> category.getName().equals(request.getFoodCategoryName()))
                .findFirst()
                .orElseGet(() -> createFoodCategory(request.getFoodCategoryName()));

        User newUser = UserConverter.toUser(request,foodCategory);
        return userRepository.save(newUser);
    }

    // FoodCategory 생성 메서드 - 해당 카테고리가 없으면 생성을 하도록 함
    private FoodCategory createFoodCategory(String categoryName) {
        FoodCategory newCategory = FoodCategory.builder()
                .name(categoryName)
                .build();
        return foodCategoryRepository.save(newCategory);
    }
}
