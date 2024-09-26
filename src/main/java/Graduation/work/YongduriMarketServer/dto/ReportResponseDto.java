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
    private String contents;
    private String answer;
    private ReportStatus reportStatus;
    private Integer reason;
    private ReportType reportType;
    private Long roomId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReportResponseDto getReportDto(Report report) {
        return new ReportResponseDto(
                report.getReportId(),
                report.getUserId().getStudentId(),
                report.getToUserId().getStudentId(),
                report.getContents(),
                report.getAnswer(),
                report.getReportStatus(),
                report.getReason(),
                report.getReportType(),
                report.getRoomId(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }


}