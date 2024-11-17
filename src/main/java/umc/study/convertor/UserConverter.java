package umc.study.convertor;

import umc.study.domain.FoodCategory;
import umc.study.domain.User;
import umc.study.web.dto.UserDTO.UserRequestDTO;
import umc.study.web.dto.UserDTO.UserResponseDTO;

import java.time.LocalDateTime;

public class UserConverter {

    // UserResponseDTO를 받아서 User를 확인하는 메소드
    public static UserResponseDTO.JoinUserResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinUserResultDTO.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // UserRequestDTO를 User 객체로 바꿔주는 메소드
    public static User toUser(UserRequestDTO.JoinUserDTO request, FoodCategory foodCategory) {
        return User.builder()
                .username(request.getUsername())
                .address(request.getAddress())
                .gender(request.getGender())
                .birthDate(request.getBirthdate())
                .foodCategory(foodCategory) // 단일 FoodCategory를 설정
                .build();
    }


}
