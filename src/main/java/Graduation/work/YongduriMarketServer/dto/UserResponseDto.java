package Graduation.work.YongduriMarketServer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long studentId;
    private String nickname;
    private Integer fileId;

}
