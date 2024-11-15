package umc.study.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.enums.RegionName;
import umc.study.repository.RegionRepository.RegionRepository;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    //지역 이름 가져오기
    public RegionName getRegionName(Long id) {
        RegionName name = regionRepository.findNameById(id);
        return name;
    }
}
