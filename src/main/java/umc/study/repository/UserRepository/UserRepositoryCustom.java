package umc.study.repository.UserRepository;

import umc.study.domain.UserPointDTO;

public interface UserRepositoryCustom {
    //특정 사용자의 총 포인트를 포함하여 유저 정보(이름)를 조회하는 쿼리
    UserPointDTO dynamicQueryWithBooleanBuilder(Long userId);

}
