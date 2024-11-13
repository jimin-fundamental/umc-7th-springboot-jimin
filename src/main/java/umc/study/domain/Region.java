package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;

import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.RegionName;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "region_name", nullable = false, length = 10)
    private RegionName regionName;



}
