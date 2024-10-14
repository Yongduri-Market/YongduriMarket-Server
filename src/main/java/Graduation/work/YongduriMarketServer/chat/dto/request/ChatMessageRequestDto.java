package Graduation.work.YongduriMarketServer.chat.dto.request;

import Graduation.work.YongduriMarketServer.chat.entity.ChatMessage;
import Graduation.work.YongduriMarketServer.chat.entity.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {
    private FileInfo file;
    private String message;
    private Long senderId;
    private Long recipientId;

    @Builder
    public ChatMessageRequestDto(String message, Long senderId, Long recipientId, FileInfo fileInfo) {
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.file = fileInfo;
    }

    public ChatMessage toEntity(User sender, User recipient, ChatRoom chatRoom, FileInfo fileInfo) {
        return ChatMessage.builder()
                .message(this.message)
                .sender(sender)
                .recipient(recipient)
                .chatRoom(chatRoom)
                .fileInfo(fileInfo)
                .timestamp(LocalDateTime.now())
                .build();
    }
}