package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.ReportCategory;
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
    private Long studentId;
    private String reportContents;
    private String reportAnswer;
    private ReportStatus reportStatus;
    private Long fileId;
    private ReportCategory reportCategory;
    private LocalDateTime createdAt;

    public static ReportResponseDto GetReportDto(Report report) {
        return new ReportResponseDto(
                report.getReportId(),
                report.getUser().getStudentId(),
                report.getReportContents(),
                report.getReportAnswer(),
                report.getReportStatus(),
                report.getFileId(),
                report.getReportCategory(),
                report.getCreatedAt()
        );
    }


}
