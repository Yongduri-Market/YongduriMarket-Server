package Graduation.work.YongduriMarketServer.dto;

import lombok.Getter;
import lombok.Setter;

public class ReportRequestDto {
    @Getter
    @Setter
    public class CreateDTO {
        private String reportContents;
        private Long fileId;
        @Override
        public String toString() {
            return "CreateDTO{" +
                    ", reportContents='" + reportContents + '\'' +
                    ", fileId='" + fileId + '\'' +
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
