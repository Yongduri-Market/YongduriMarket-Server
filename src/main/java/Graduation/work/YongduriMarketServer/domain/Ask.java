package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.domain.state.AskCategory;
import Graduation.work.YongduriMarketServer.domain.state.StatusCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long askId;

    @JoinColumn(name = "studentId",nullable = false)
    @ManyToOne
    private User user;

    @Column(columnDefinition = "TEXT")
    private String title;


    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private StatusCategory status;

    @Column
    private String answer;

    @Column
    private String file;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private AskCategory askCategory;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

}
