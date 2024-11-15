package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {
    @Id //기본 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //해당 내용은 JPA가 통신을 하는 DBMS의 방식을 따른다는 뜻
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;
}
