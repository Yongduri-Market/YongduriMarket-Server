package Graduation.work.YongduriMarketServer.dto;
<<<<<<< HEAD
=======
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
import lombok.Getter;
import lombok.Setter;
public class ReportRequestDto {




    @Getter
    @Setter
<<<<<<< HEAD
    public static class UserReportDto {
        private String reportContents;
        private Long toUserId;
        private Long roomId;
        private Integer userReportReason;
=======
    public class UserReportDto {
        private String contents;
        private Long toUserId;
        private Long roomId;
        private Integer reason;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    }
    @Getter
    @Setter
    public class BugReportDto {
<<<<<<< HEAD
        private String reportContents;
=======
        private String contents;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    }

    @Getter
    @Setter
<<<<<<< HEAD
    public class AnswerDTO {
        private Long reportId;
        private String  reportAnswer;
=======
    public static class  AnswerDTO {
        private Long reportId;
        private String  answer;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

    }

    @Getter
    @Setter
    public class DetailDto {
        private Long reportId;
    }
}