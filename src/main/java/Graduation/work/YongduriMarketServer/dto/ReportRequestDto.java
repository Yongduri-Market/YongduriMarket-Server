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
        @Override
        public String toString() {
            return "CreateDTO{" +
                    ", reportContents='" + reportContents + '\'' +
                    ", reportCategory='" + reportCategory + '\'' +
                    '}';
        }
    }
    @Getter
    @Setter
    public class AnswerDTO {
        private Long reportId;
        private String  reportAnswer;
        @Override
        public String toString() {
            return "AnswerDTO{" +
                    ", reportId='" + reportId + '\'' +
                    ", reportAnswer='" + reportAnswer + '\'' +
                    '}';
        }
    }
    @Getter
    @Setter
    public class DeleteDTO {
        private Long reportId;
        @Override
        public String toString() {
            return "DeleteDTO{" +
                    ", reportId='" + reportId + '\'' +
                    '}';
        }
    }





}
