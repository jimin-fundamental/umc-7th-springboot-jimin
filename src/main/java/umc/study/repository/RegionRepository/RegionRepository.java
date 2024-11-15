package umc.study.repository.RegionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Region;
import umc.study.domain.enums.RegionName;

public interface RegionRepository extends JpaRepository<Region, Long> {
    RegionName findNameById(Long id);
}
