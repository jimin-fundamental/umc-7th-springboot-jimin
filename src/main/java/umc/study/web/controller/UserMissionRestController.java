package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.UserMissionConverter;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.web.dto.UserMissionDTO.UserMissionRequestDTO;
import umc.study.web.dto.UserMissionDTO.UserMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/{mission-id}") //회원가입
    public ApiResponse<UserMissionResponseDTO.JoinResultDTO> addUserMission(
            @PathVariable(value = "mission-id") Long missionId,
            @RequestBody @Valid UserMissionRequestDTO.JoinDto request){
        //@RequestHeader("Authorization") String accessToken) 나중에 추가
        UserMission userMission = userMissionCommandService.addUserMission(missionId, request);
        return ApiResponse.onSuccess(UserMissionConverter.toJoinResultDTO(userMission));
    }
}