
package Graduation.work.YongduriMarketServer.chat.controller;

import Graduation.work.YongduriMarketServer.chat.dto.request.ChatMessageRequestDto;
import Graduation.work.YongduriMarketServer.chat.dto.response.ChatMessageResponseDto;
import Graduation.work.YongduriMarketServer.chat.dto.response.ChatRoomDto;
import Graduation.work.YongduriMarketServer.chat.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import org.springframework.web.util.HtmlUtils;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/greetings")
<<<<<<< HEAD
    public ChatMessageResponseDto sendMessage(@PathVariable(value = "roomId") String roomId, @Payload ChatMessageRequestDto requestDto) {
=======
    public void sendMessage(@PathVariable(value = "roomId") String roomId, @Payload ChatMessageRequestDto requestDto) {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        ChatMessageResponseDto responseDto = chatService.sendMessage(requestDto, requestDto.getRecipientId());
        messagingTemplate.convertAndSendToUser(String.valueOf(requestDto.getRecipientId()), "/queue/messages", responseDto);

        log.info("Message sent to room {}: {}", roomId, responseDto.toString());
<<<<<<< HEAD

        return responseDto;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable(value = "messageId") Long messageId) {
        log.info("메시지 삭제  - 메시지 ID: {}", messageId);
        chatService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<ChatRoomDto>> getChatRoomsByUser(@PathVariable(value = "userId") Long userId) {
        log.info("채팅방 조회 요청 - 사용자 ID: {}", userId);
        List<ChatRoomDto> chatRooms = chatService.getChatRoomsByUser(userId);
        return ResponseEntity.ok(chatRooms);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getMessagesByRoom(@PathVariable(value = "roomId") Long roomId) {
        log.info("채팅방 메시지 조회 요청 - 채팅방 ID: {}", roomId);
        List<ChatMessageResponseDto> messages = chatService.getMessagesByRoom(roomId);
        return ResponseEntity.ok(messages);
    }
    @DeleteMapping("/rooms/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable(value = "chatRoomId") Long chatRoomId) {
        log.info("채팅방 삭제 요청 - 채팅방 ID: {}", chatRoomId);
        chatService.deleteChatRoom(chatRoomId);
        return ResponseEntity.ok().build();
    }
<<<<<<< HEAD
=======

    @GetMapping("/chatting")
    public String ResponseEntity(HttpServletResponse response) {
        //HttpHeaders headers = new HttpHeaders();
        //response.setHeader("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJka3N3bmR1czY5ODhAZ21haWwuY29tIiwiaWF0IjoxNzI3MjI1NTM0LCJleHAiOjE3MjcyMjczMzQsInN1YiI6Iu2VmOujqCIsInN0dWRlbnRJZCI6MjAyMTc4MDQ5LCJuaWNrbmFtZSI6Iu2VmOujqCIsInJvbGUiOiJVU0VSIn0.cbN_QRShSNcwN-66jSqtvYJ0v3VHzmUuZ5V4J11yoew");
        return "Chat";
    }
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
}
