package Graduation.work.YongduriMarketServer.dto;
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {
    private Long reportId;
    private Long userId;
    private Long toUserId;
<<<<<<< HEAD
    private String reportContents;
    private String reportAnswer;
    private ReportStatus reportStatus;
=======
    private String contents;
    private String answer;
    private ReportStatus reportStatus;
    private Integer reason;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    private ReportType reportType;
    private Long roomId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReportResponseDto getReportDto(Report report) {
        return new ReportResponseDto(
                report.getReportId(),
                report.getUserId().getStudentId(),
                report.getToUserId().getStudentId(),
<<<<<<< HEAD
                report.getReportContents(),
                report.getReportAnswer(),
                report.getReportStatus(),
=======
                report.getContents(),
                report.getAnswer(),
                report.getReportStatus(),
                report.getReason(),
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
                report.getReportType(),
                report.getRoomId(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }


}