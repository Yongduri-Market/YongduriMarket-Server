package Graduation.work.YongduriMarketServer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
    private long boardId;
    private long studentId;
    private long tradePlace;
    private long tradeMethod;
    private TradeStatus status;
    private long salesCategory;
    private String boardTitle;
    private String boardContent;
    private long hits;
    private long price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static BoardResponseDto GetBoardDetailDto(Board board){
        return new BoardResponseDto(
                board.getUser().getStudentId(),
                board.getBoardId(),
                board.getTradePlace(),
                board.getTradeMethod(),
                board.getStatus(),
                board.getSalesCategory(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getHits(),
                board.getPrice(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}