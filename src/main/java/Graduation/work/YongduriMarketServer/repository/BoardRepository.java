package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /*@Override
    public void updateCount(Board board1, boolean b) {
        if (b) {
            queryFactory.update(board)
                    .set(board.likeCount, board.likeCount.add(1))
                    .where(board.eq(board1))
                    .execute();
        } else {
            queryFactory.update(board)
                    .set(board.likeCount, board.likeCount.subtract(1))
                    .where(board.eq(board1))
                    .execute();
        }
    }*/
    Optional<Board> findByBoardId(Long boardId);
}