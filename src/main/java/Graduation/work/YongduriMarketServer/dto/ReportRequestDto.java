package Graduation.work.YongduriMarketServer.dto;
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import lombok.Getter;
import lombok.Setter;
public class ReportRequestDto {




    @Getter
    @Setter
    public class UserReportDto {
        private String contents;
        private Long toUserId;
        private Long roomId;
        private Integer reason;
    }
    @Getter
    @Setter
    public class BugReportDto {
        private String contents;
    }

    @Getter
    @Setter
    public static class  AnswerDTO {
        private Long reportId;
        private String  answer;

    }

    @Getter
    @Setter
    public class DetailDto {
        private Long reportId;
    }
}