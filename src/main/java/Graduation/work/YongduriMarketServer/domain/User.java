package Graduation.work.YongduriMarketServer.domain;

<<<<<<< HEAD
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @Column(nullable = false, unique = true, length = 255)
    private Long studentId;

    @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(nullable = false,  length = 20)
    private String name;

    @Column(nullable = false,  length = 20)
    private String nickname;

    @Column(nullable = false,  length = 20)
    private Long phone;

   // @Column(nullable = false)
    //private Integer fileId;

    @Column(nullable = false)
    private Long birthDate;

    //연, 월, 일 정보만 표현

    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt; //연,월,일,시,분,초

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

<<<<<<< HEAD
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;


    public User(Long studentId,String password, String name, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, FileInfo fileInfo  ) {
=======

    public User(Long studentId,String password, String name, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt  ){
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
<<<<<<< HEAD
        this.fileInfo = fileInfo;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void encodedPwd(PasswordEncoder passwordEncoder){this.password = passwordEncoder.encode(password);}
    public void setRefreshToken(String refreshToken){this.refreshToken = refreshToken;}

    public User(Long studentId, String refreshToken){
        this.studentId = studentId;
        this.refreshToken = refreshToken;
    }
    public User update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;
    }
}
