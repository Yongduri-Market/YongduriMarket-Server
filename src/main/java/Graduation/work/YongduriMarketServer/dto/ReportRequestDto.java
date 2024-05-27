package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.ReportCategory;
import lombok.Getter;
import lombok.Setter;

public class ReportRequestDto {
    @Getter
    @Setter
    public class CreateDTO {
        private String reportContents;
        private Long fileId;
        private Long reportCategory;
        @Override
        public String toString() {
            return "CreateDTO{" +
                    ", reportContents='" + reportContents + '\'' +
                    ", fileId='" + fileId + '\'' +
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





}
