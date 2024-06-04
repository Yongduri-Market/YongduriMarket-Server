package Graduation.work.YongduriMarketServer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor // 기본 생성자 추가

public class RegisterDto {


    private String  userEmail;
    private String  pwd;
    private String  name;
    private String  nickname;
    private Integer phone;
    private String fileId;
    private  LocalDateTime birthDate;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;






}