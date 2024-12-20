package umc.study.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPointDTO {
    private Long userId;
    private String userName;
    private Integer totalPoints;


}
