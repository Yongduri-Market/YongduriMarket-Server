package Graduation.work.YongduriMarketServer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)  // FetchType 설정
    @JoinColumn(name = "student_id", nullable = false) // nullable 속성 설정
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)  // FetchType 설정
    @JoinColumn(name = "board_id", nullable = false) // nullable 속성 설정
    private Board board;

    @CreationTimestamp
    @Column(nullable = false)  // nullable 속성 설정
    private LocalDateTime createdAt;
}
