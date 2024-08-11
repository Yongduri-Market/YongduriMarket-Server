package Graduation.work.YongduriMarketServer.dto;
import Graduation.work.YongduriMarketServer.domain.state.ReportCategory;
import lombok.Getter;
import lombok.Setter;
public class ReportRequestDto {
    @Getter
    @Setter
    public class CreateDTO {
        private String reportContents;
        private Long reportCategory;

    }
    @Getter
    @Setter
    public class AnswerDTO {
        private Long reportId;
        private String  reportAnswer;

    }

    @Getter
    @Setter
    public class DeleteDTO {
        private Long reportId;

    }

}