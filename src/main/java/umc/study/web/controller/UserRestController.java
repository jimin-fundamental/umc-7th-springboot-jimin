package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.RestaurantConverter;
import umc.study.convertor.UserConverter;
import umc.study.convertor.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.enums.Status;
import umc.study.domain.mapping.UserMission;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.service.UserService.UserCommandService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistId;
import umc.study.web.dto.RestaurantDTO.RestaurantResponseDTO;
import umc.study.web.dto.UserDTO.UserRequestDTO;
import umc.study.web.dto.UserDTO.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserMissionCommandService userMissionCommandService;
    private final MissionQueryService missionQueryService;

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

    //내가 진행중인 미션 조회
    @GetMapping("/missions/lists/{userId}")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다! - 스프링 시큐리티 구현 안해서 이렇게 진행")
    })
    public ApiResponse<UserResponseDTO.UserMissionPreViewListDTO> getMissionList (@ExistId @PathVariable(name = "userId") Long userId, @CheckPage @RequestParam(name = "page") Integer page, @RequestParam(name = "status") Status status){
        int adjustedPage = page-1;
        Page<UserMission> missionList= missionQueryService.getUserMissionList(userId, status, adjustedPage);
        return ApiResponse.onSuccess(UserConverter.userMissionPreViewListDTO(missionList));
    }

}