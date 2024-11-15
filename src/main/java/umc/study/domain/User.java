package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 해당 클래스가 JPA의 엔티티임을 명시
@Entity
// lombok에서 제공해주는 것으로, getter를 만들어주는 어노테이션
@Getter
//자바의 디자인 패턴 중 하나인 빌더 패턴을 사용하기 위함
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class User extends BaseEntity {
    @Id //기본 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //해당 내용은 JPA가 통신을 하는 DBMS의 방식을 따른다는 뜻
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 1024)
    private String address;

    @Enumerated(EnumType.STRING) // enum을 entity에 적용
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferCategoryId")
    private FoodCategory foodCategory;

    @ColumnDefault("0")
    private Integer point;

    //review, point, mission, alarm에 대한 양방향매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Point> points = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alarm> alarms = new ArrayList<>();

}
