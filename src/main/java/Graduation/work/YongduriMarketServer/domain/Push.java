package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.domain.state.PushType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Push {
    @Id
    @Column(unique = true, nullable = false)
    private Long pushId;

    @JoinColumn(name = "student_id",nullable = false)
    @ManyToOne
    private User user;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PushType pushCategory;

    @Column
    private String pushContents;

    @Column
    private Integer pushCheck;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;

}
