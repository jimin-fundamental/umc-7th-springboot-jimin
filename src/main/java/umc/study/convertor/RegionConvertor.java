package umc.study.convertor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.enums.RegionName;
import umc.study.service.RegionService.RegionService;

//@Component
//@RequiredArgsConstructor
//public class RegionConvertor {
//    private final RegionService regionService;
//    //regionID로 region 객체 생성
//    public static Region toRegion(Long regionId) {
//        if (regionId == null) {
//            throw new IllegalArgumentException(ErrorStatus.REGION_NOT_FOUND.toString());
//        }
//
//        RegionName name = regionService.getRegionName(regionId);
//        if (name == null) {
//            throw new IllegalArgumentException(ErrorStatus.REGION_NOT_FOUND.toString());
//        }
//
//        return Region.builder()
//                .id(regionId)
//                .regionName(name)
//                .build();
//    }
//
//
//}
