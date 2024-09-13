package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradePlaceType;
import lombok.*;
import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private Long userId;
    private TradePlaceType place;
    private TradeMethodType method;
    private TradeStatus status;
    private SalesType sales;
    private String boardTitle;
    private String boardContent;
    private Integer price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static BoardResponseDto getBoardDto(Board board){
        return new BoardResponseDto(

                board.getBoardId(),
                board.getUserId().getStudentId(),
                board.getPlace(),
                board.getMethod(),
                board.getStatus(),
                board.getSales(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getPrice(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }


}