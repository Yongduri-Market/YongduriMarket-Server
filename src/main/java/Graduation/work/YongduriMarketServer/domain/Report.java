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

    @Column
    private String reportContents;
    @Column
    private String reportAnswer;

    @Column
    private ReportStatus reportStatus;

    @Column
    private Long fileId;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;



}
