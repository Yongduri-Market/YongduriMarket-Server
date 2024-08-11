package Graduation.work.YongduriMarketServer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class ReviewRequestDto {

    @Data
    public static class ReviewIdDto{
        private long reviewId;
    }
    @Data
    public static class ReviewDetailDto{
        private long reviewee_Id;
    }
    @Data
    public static class ReviewWriteDto{
        private Long reviewId;
        private Long board_Id;
        private Long reviewee_Id;
        private Long reviewer_Id;
        private Integer assessment;
        private Integer scope;
        private Integer reviewStatus = 1;
        private LocalDateTime createdAt;
    }
}
