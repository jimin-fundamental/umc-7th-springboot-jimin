package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.domain.enums.Status;

//JpaRepository를 상속받아 기본 CRUD 기능을 제공하면서, MissionRepositoryCustom을 확장하여 사용자 정의 쿼리 기능도 추가할 수 있도록 설계
public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom{
    Page<Mission> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
    Page<Mission> findAllByUser(User user, PageRequest pageRequest);

    //JpaRepository<Mission, Long>:
        //Mission: 엔티티에 대해 기본적인 CRUD 기능을 제공
        //Long: Mission 엔티티의 기본 키(id) 타입

    //MissionRepositoryCustom:
        //MissionRepositoryCustom 인터페이스를 확장하여 동적 쿼리 메서드
}
