package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto {

    @Getter
    public static class LikeDto{
        private long studentId;
        private long boardId;

        public LikeDto(long studentId,long boardId){
            this.studentId = studentId;
            this.boardId = boardId;
        }
    }
    @Data
    public static class boardIdDto{
        private long boardId;
    }

    @Data
    public static class DetailDto {
        private Long boardId;
    }
    @Data
    public static class BoardWriteDto{
        private long boardId;
        private long studentId;
        private long tradePlace;
        private long tradeMethod;
        private TradeStatus status = TradeStatus.ACTIVE;
        private long salesCategory;
        private String boardTitle;
        private String boardContent;
        private long hits = 1;
        private long price;
        private LocalDateTime createdAt;
    }
    @Data
    public static class BoardEditDto{
        private long boardId;
        private long studentId;
        private long tradePlace;
        private long tradeMethod;
        private TradeStatus status = TradeStatus.ACTIVE;
        private long salesCategory;
        private String boardTitle;
        private String boardContent;
        private long hits = 1;
        private long price;
        private LocalDateTime updatedAt;
    }


}