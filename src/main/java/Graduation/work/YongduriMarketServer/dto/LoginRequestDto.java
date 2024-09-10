package Graduation.work.YongduriMarketServer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private Long  studentId;
    private String password;

}
