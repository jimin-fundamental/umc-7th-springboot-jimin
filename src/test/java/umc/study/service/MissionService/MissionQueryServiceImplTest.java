package umc.study.service.MissionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.study.domain.Mission;
import umc.study.domain.mapping.UserMission;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MissionQueryServiceImplTest {

    @Autowired //스프링 컨텍스트에서 자동으로 빈(Bean)을 주입
    private MissionQueryService service;

    // 우리는 데이터를 새로 생성하지 않기 때문에 @BeforeEach나 @AfterEach가 필요없음

    @Test
    void findUserMissionsByCriteria() {
        //Test는 Given(상황)-> When(실행, 검증하고 싶은 것) -> Then(결과)으로 실행

        //Given
        Long userId = 1L;
        String status = "COMPLETE";
        LocalDateTime createdAt = LocalDateTime.of(2024, 11, 7, 13, 45, 34).plusSeconds(1);
        int pageSize = 3;

        //When
        // 동적 쿼리를 실행
        List<UserMission> filteredMissions = service.findUserMissionsByCriteria(userId, status, createdAt, pageSize);


        //Then
        // 리스트가 비어 있는지 확인
        System.out.println("Filtered Missions Size: " + filteredMissions.size());

        for (UserMission userMission : filteredMissions) {
            System.out.print(userMission.getId() + "+");
            if (userMission.getMission() != null) {
                System.out.println("Mission Name: " + userMission.getMission().getName());
            }
        }

    }

    @Test
    void findUserMissionsByRegionCriteria() {
        //Given
        Long userId = 1L;
        Long regionId = 1L;
        LocalDateTime createdAt = LocalDateTime.of(2024, 11, 7, 13, 45, 34).plusSeconds(1);
        int pageSize = 3;

        //When
        // 동적 쿼리를 실행
        List<Mission> regionFilteredMissions = service.findUserMissionsByRegionCriteria(userId, regionId, createdAt, pageSize);

        //Then
        // 리스트가 비어 있는지 확인
        System.out.println("Filtered Missions Size: " + regionFilteredMissions.size());

        // 조회된 미션이 있는지 확인 후 출력
        if (!regionFilteredMissions.isEmpty()) {
            System.out.println("Region " + regionFilteredMissions.get(0).getRegion().getRegionName() + "에서 가능한 미션들은:");
            regionFilteredMissions.forEach(mission -> {
                System.out.println("Mission Name: " + mission.getName());
            });
        } else {
            System.out.println("해당 지역에서 수행 가능한 미션이 없습니다.");
        }
    }
}