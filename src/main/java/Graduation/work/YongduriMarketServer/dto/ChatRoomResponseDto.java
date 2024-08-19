package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomResponseDto {

    private Long roomId;
    private Long boardId;
    private Long seller;
    private Long buyer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static ChatRoomResponseDto getChatRoomDTO(ChatRoom chatRoom) {
        return new ChatRoomResponseDto(
                chatRoom.getRoomId(),
                chatRoom.getBoard().getBoardId(),
                chatRoom.getSeller().getStudentId(),
                chatRoom.getBuyer().getStudentId(),
                chatRoom.getCreatedAt(),
                chatRoom.getUpdatedAt()
        );
    }
}
