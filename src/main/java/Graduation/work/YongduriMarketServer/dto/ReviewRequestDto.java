package Graduation.work.YongduriMarketServer.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ReviewRequestDto {



    @Getter
    @Setter
    public static class ReviewWriteDto{
        private Long boardId;
        private Long roomId;
        private Long revieweeId;
        private Integer assessment;
        private Integer scope;
        private Integer reviewStatus;
        private LocalDateTime createdAt;
    }
}
