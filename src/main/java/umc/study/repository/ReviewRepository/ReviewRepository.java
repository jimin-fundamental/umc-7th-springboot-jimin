package umc.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
    //스프링데이터JPA에서 메서드 만들어줌
    Page<Review> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
}
