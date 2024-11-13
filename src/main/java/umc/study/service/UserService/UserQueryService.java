    package umc.study.service.UserService;

    import umc.study.web.dto.UserPointDTO;

    public interface UserQueryService {
        UserPointDTO findUserInformation(Long id);
    }
