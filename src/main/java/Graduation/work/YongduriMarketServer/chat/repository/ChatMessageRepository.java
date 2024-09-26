package Graduation.work.YongduriMarketServer.chat.repository;

import Graduation.work.YongduriMarketServer.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    /** ChatMessage 검색조회 - 기본정렬순, Message 검색 */
    List<ChatMessage> findByChatRoomId(Long chatRoomId);

}