package Graduation.work.YongduriMarketServer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom chatRoom;


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User senderNickname;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverNickname;

    @Column(nullable = false)
    private String message;

    // 0 채팅 참여함, 1 채팅 참여 안 함
    @Column
    private Integer chattingStatus;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime sentAt;




    public Long getRoomId() {
        return this.chatRoom.getRoomId();
    }
}
