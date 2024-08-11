package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomId(Long roomId);

    List<ChatRoom> findByOrderByCreatedAtDesc();

}
