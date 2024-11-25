
package Graduation.work.YongduriMarketServer.chat.dto.response;

import Graduation.work.YongduriMarketServer.chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatRoomDto {
    private Long id;
    private Long userId;
    private String userNickname;

    public ChatRoomDto(ChatRoom entity, Long currentUserId) {
        this.id = entity.getId();
        if (entity.getUser1().getStudentId().equals(currentUserId)) {
            this.userId = entity.getUser2().getStudentId();
            this.userNickname = entity.getUser2().getNickname();
        } else {
            this.userId = entity.getUser1().getStudentId();
            this.userNickname = entity.getUser1().getNickname();

        }
    }
}
