package Graduation.work.YongduriMarketServer.chat.service;


import Graduation.work.YongduriMarketServer.chat.dto.request.ChatMessageRequestDto;
import Graduation.work.YongduriMarketServer.chat.dto.response.ChatMessageResponseDto;
import Graduation.work.YongduriMarketServer.chat.dto.response.ChatRoomDto;
import Graduation.work.YongduriMarketServer.chat.entity.ChatMessage;
import Graduation.work.YongduriMarketServer.chat.entity.ChatRoom;
import Graduation.work.YongduriMarketServer.chat.repository.ChatMessageRepository;
import Graduation.work.YongduriMarketServer.chat.repository.ChatRoomRepository;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;


    // 채팅 보내기.
    @Transactional
    public ChatMessageResponseDto sendMessage(final ChatMessageRequestDto requestDto, Long recipientId) {
        User sender = userRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("발신자의 정보를 찾을 수 없습니다. Student ID = " + requestDto.getSenderId()));

        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("수신자의 정보를 찾을 수 없습니다. Student ID = " + recipientId));

        ChatRoom chatRoom = findOrCreateChatRoom(sender, recipient);
        FileInfo fileInfo = requestDto.getFile() != null ? requestDto.getFile() : null;

        ChatMessage chatMessage = requestDto.toEntity(sender, recipient, chatRoom, fileInfo);
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        return new ChatMessageResponseDto(savedMessage);
    }

    @Transactional
    public ChatRoom findOrCreateChatRoom(User sender, User recipient) {
        return chatRoomRepository.findByUser1AndUser2(sender, recipient)
                .orElseGet(() -> {
                    ChatRoom newChatRoom = ChatRoom.builder()
                            .user1(sender)
                            .user2(recipient)
                            .build();
                    return chatRoomRepository.save(newChatRoom);
                });
    }


    @Transactional
    public void deleteMessage(Long messageId) {
        chatMessageRepository.deleteById(messageId);
    }

    @Transactional(readOnly = true)
    public List<ChatRoomDto> getChatRoomsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. 사용자 ID = " + userId));
        return chatRoomRepository.findByUser1OrUser2(user, user).stream()
                .map(chatRoom -> new ChatRoomDto(chatRoom, userId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId) {

        chatRoomRepository.deleteById(chatRoomId);
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponseDto> getMessagesByRoom(Long roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomId(roomId);
        return messages.stream()
                .map(ChatMessageResponseDto::new)
                .collect(Collectors.toList());
    }
}