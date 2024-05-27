package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.domain.state.ReportCategory;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reportId;

    @JoinColumn(name = "studentId",nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String reportContents;
    @Column
    private String reportAnswer;


    private ReportStatus reportStatus;

    @Column(nullable = false)
    private Long fileId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ReportCategory reportCategory;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;



}
