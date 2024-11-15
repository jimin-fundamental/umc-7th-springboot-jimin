package umc.study.repository.RestaurantRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    @Query("SELECT r.name FROM Restaurant r WHERE r.id = :id")
    String getRestaurantName(@Param("id") Long id);
}
