package umc.study.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.study.domain.UserPointDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserQueryServiceImplTest {
    @Autowired
    private UserQueryService userQueryService;

    @Test
    void findUserInformation() {
        //Given
        Long userId = 1L;

        //when
        UserPointDTO thisUser = userQueryService.findUserInformation(userId);

        //Then
        assertNotNull(thisUser);
        System.out.println(thisUser.getUserId() + "+" + thisUser.getUserName() + "+" + thisUser.getTotalPoints());
    }
}