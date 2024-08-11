package Graduation.work.YongduriMarketServer.websocket;


import Graduation.work.YongduriMarketServer.domain.ChatMessage;
import Graduation.work.YongduriMarketServer.domain.ChatRoom;

import Graduation.work.YongduriMarketServer.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler  extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatRoomService chatRoomService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Received message: " + payload);

        // 메시지를 ChatMessage 객체로 변환
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        // 채팅방 ID를 기준으로 채팅방을 찾기
        ChatRoom chatRoom = chatRoomService.findByRoomId(chatMessage.getRoomId());

        // 채팅방에 메시지 전달
        chatRoom.handleMessage(session, chatMessage, objectMapper);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected: " + session.getId());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Disconnected: " + session.getId());

    }



}
