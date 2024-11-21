package umc.study.convertor;

import org.springframework.data.domain.Page;
import umc.study.domain.FoodCategory;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;
import umc.study.web.dto.UserDTO.UserRequestDTO;
import umc.study.web.dto.UserDTO.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static umc.study.domain.mapping.QUserMission.userMission;

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

    //내가 현재 진행중인 미션 DTO
    public static UserResponseDTO.UserMissionPreViewDTO userMissionPreViewDTO(UserMission userMission){
        return UserResponseDTO.UserMissionPreViewDTO.builder()
                .userId(userMission.getUser().getId()) //객체그래프탐색 - ManyToOne으로 지정한 Member 통해 가져오기
                .title(userMission.getMission().getName())
                .status(userMission.getStatus())
                .createdAt(userMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static UserResponseDTO.UserMissionPreViewListDTO userMissionPreViewListDTO(Page<UserMission> userMissionList){
        List<UserResponseDTO.UserMissionPreViewDTO> userMissionPreViewDTOList = userMissionList.stream()
                .map(UserConverter::userMissionPreViewDTO).collect(Collectors.toList());

        return UserResponseDTO.UserMissionPreViewListDTO.builder()
                .isLast(userMissionList.isLast())
                .isFirst(userMissionList.isFirst())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .listSize(userMissionPreViewDTOList.size())
                .userMissionList(userMissionPreViewDTOList)
                .build();
    }


}
