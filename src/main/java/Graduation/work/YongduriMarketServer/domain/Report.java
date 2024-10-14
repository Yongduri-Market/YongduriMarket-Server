package Graduation.work.YongduriMarketServer.domain;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
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
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reportId;

    // 신고하는 사람
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User userId;

    // 신고 당하는 사람
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = true)  // nullable 설정
    private User toUserId;

    @Column(columnDefinition = "TEXT")
    private String reportContents;

    @Column(columnDefinition = "TEXT")
    private String reportAnswer;

    //0 대기중, 1 답변 완료
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ReportStatus reportStatus;


    //0 유저 신고 , 1 앱버그 신고
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ReportType reportType;

    @Column
    private Long roomId;

    @OneToOne
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}