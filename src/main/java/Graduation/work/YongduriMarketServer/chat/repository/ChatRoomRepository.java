package Graduation.work.YongduriMarketServer.chat.repository;
import Graduation.work.YongduriMarketServer.chat.entity.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("SELECT c FROM ChatRoom c WHERE (c.user1 = :user1 AND c.user2 = :user2) OR (c.user1 = :user2 AND c.user2 = :user1)")
    Optional<ChatRoom> findByUser1AndUser2(@Param(value = "user1") User user1,@Param(value = "user2") User user2);

    List<ChatRoom> findByUser1OrUser2(@Param(value = "user1") User user1,@Param(value = "user2") User user2);


}