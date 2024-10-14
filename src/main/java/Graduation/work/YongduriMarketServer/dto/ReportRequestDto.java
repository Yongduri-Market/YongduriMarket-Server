package Graduation.work.YongduriMarketServer.dto;
import lombok.Getter;
import lombok.Setter;
public class ReportRequestDto {




    @Getter
    @Setter
    public static class UserReportDto {
        private String reportContents;
        private Long toUserId;
        private Long roomId;
        private Integer userReportReason;
    }
    @Getter
    @Setter
    public static class BugReportDto {
        private String reportContents;
    }

    @Getter
    @Setter
    public static class AnswerDTO {
        private Long reportId;
        private String  reportAnswer;

    }


}