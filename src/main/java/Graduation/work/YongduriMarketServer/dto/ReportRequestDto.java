package Graduation.work.YongduriMarketServer.dto;
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import lombok.Getter;
import lombok.Setter;
public class ReportRequestDto {




    @Getter
    @Setter
    public class UserReportDto {
        private String reportContents;
        private Long toUserId;
        private Long roomId;
        private Integer userReportReason;
    }
    @Getter
    @Setter
    public class BugReportDto {
        private String reportContents;
    }

    @Getter
    @Setter
    public class AnswerDTO {
        private Long reportId;
        private String  reportAnswer;

    }

    @Getter
    @Setter
    public class DetailDto {
        private Long reportId;
    }
}