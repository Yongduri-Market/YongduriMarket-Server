package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@Transactional
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByOrderByCreatedAtDesc();
    Optional<Board> findByBoardId(Long boardId);

}