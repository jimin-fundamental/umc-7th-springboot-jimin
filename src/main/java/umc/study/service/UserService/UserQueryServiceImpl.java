package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserPointDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public UserPointDTO findUserInformation(Long id) {
        UserPointDTO thisUser = userRepository.dynamicQueryWithBooleanBuilder(id);
        return thisUser;
    }
}
