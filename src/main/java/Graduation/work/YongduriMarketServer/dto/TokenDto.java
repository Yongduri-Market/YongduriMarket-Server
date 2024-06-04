package Graduation.work.YongduriMarketServer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TokenDto {
    private String accessToken;
    private String refreshToken;
}