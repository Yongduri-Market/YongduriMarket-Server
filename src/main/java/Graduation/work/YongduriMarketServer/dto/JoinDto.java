package Graduation.work.YongduriMarketServer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class JoinDto {


    private Long  studentId;
    private String  password;
    private String  name;
    private String  nickname;
    private Long phone;
    private Long birthDate;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;




}