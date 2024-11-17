package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.UserConverter;
import umc.study.convertor.UserMissionConverter;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.service.UserService.UserCommandService;
import umc.study.web.dto.UserDTO.UserRequestDTO;
import umc.study.web.dto.UserDTO.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/register") //회원가입
    public ApiResponse<UserResponseDTO.JoinUserResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinUserDTO request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @PostMapping("/{mission-id}") //미션수행 추가
    public ApiResponse<UserResponseDTO.JoinUserMissionResultDTO> addUserMission(
            @PathVariable(value = "mission-id") Long missionId,
            @RequestBody @Valid UserRequestDTO.JoinUserMissionDTO request){
        //@RequestHeader("Authorization") String accessToken) 나중에 추가
        UserMission userMission = userMissionCommandService.addUserMission(missionId, request);
        return ApiResponse.onSuccess(UserMissionConverter.toJoinUserMissionDTO(userMission));
    }
}