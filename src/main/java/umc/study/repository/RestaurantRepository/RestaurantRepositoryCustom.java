package umc.study.repository.RestaurantRepository;

import umc.study.domain.Restaurant;
import java.util.List;


public interface RestaurantRepositoryCustom {
    // 이름과 평점에 따른 레스토랑 검색
    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Float score);
}