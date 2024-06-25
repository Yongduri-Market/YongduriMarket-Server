package Graduation.work.YongduriMarketServer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private Long  studentId;
    private String nickname;
    private TokenDto token;
}
