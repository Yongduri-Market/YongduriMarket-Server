package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.service.ChatRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomId;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "seller_id")
    @ManyToOne
    private User seller;

    @JoinColumn(name = "buyer_id")
    @ManyToOne
    private User buyer;






    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;


    // WebSocket 세션 관리
    @Transient
    private Set<WebSocketSession> sessions = new HashSet<>();

    // WebSocket 세션을 추가하는 메서드
    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    // WebSocket 세션을 제거하는 메서드
    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    // 채팅 메시지를 처리하고 해당 채팅방에 있는 모든 세션에 메시지를 전송하는 메서드
    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) {
        try {
            // ChatMessage 객체를 JSON 문자열로 변환
            String messagePayload = objectMapper.writeValueAsString(chatMessage);

            // TextMessage 객체 생성
            TextMessage textMessage = new TextMessage(messagePayload);

            // 모든 세션에 메시지 전송
            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(textMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}