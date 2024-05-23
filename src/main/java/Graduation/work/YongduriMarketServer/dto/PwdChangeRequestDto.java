package Graduation.work.YongduriMarketServer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PwdChangeRequestDto {
    private Long studentId;
    private String password;
}