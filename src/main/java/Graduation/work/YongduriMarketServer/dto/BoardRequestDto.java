package Graduation.work.YongduriMarketServer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto {
    private long boardId;
    private long userId;
    private long tradePlace;
    private long tradeMethod;
    private long tradeStatus;
    private long salesCategory;
    private String boardTitle;
    private String boardContent;
    private long hits;
    private long price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    public static class LikeDto{
        private long userId;
        private long boardId;

        public LikeDto(long userId,long boardId){
            this.userId = userId;
            this.boardId = boardId;
        }
    }
}