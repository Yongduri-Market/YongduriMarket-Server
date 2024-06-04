package Graduation.work.YongduriMarketServer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
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
}
