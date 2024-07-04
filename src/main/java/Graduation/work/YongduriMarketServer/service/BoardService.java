package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseSavedIdDto;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Board> boardList() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public BoardResponseDto boardDetail(long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
        board.updateHits(board.getHits());
        //User user = userRepository.findById(likeDto.getUserId());
        //User user = userRepository.findById(BoardRequestDto.getUserId());
        return BoardResponseDto.builder()
                //.user(user)
                .tradePlace(board.getTradePlace())
                .tradeMethod(board.getTradeMethod())
                .tradeStatus(board.getTradeStatus())
                .salesCategory(board.getSalesCategory())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .hits(board.getHits())
                .price(board.getPrice())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
    // 게시글 작성
    @Transactional
    public BoardResponseSavedIdDto boardWrite(BoardRequestDto requestDto) {
        Board board = Board.builder()
                //.userId(requestDto.getUserId())
                .tradePlace(requestDto.getTradePlace())
                .tradeMethod(requestDto.getTradeMethod())
                .tradeStatus(requestDto.getTradeStatus())
                .salesCategory(requestDto.getSalesCategory())
                .boardTitle(requestDto.getBoardTitle())
                .boardContent(requestDto.getBoardContent())
                .hits(requestDto.getHits())
                .price(requestDto.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        Board savedBoard = boardRepository.save(board);

        return BoardResponseSavedIdDto.builder()
                .savedId(savedBoard.getBoardId())
                .build();
    }

    //게시글 수정
    @Transactional
    public void boardEdit(long boardId, BoardRequestDto requestDto) {
       //1. 기존 게시글 꺼내와서
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
        //2. 게시글에 boardUpdate 덮어씌우기
        board.update(requestDto);
    }
    //게시글 삭제
    @Transactional
    public void boardDelete(long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
        boardRepository.delete(board);
    }

    // 좋아요 추가
    /*public void likeInsert(BoardRequestDto.LikeDto likeDto) throws Exception {
        User user = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));
        Board board = boardRepository.findById(likeDto.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글 정보를 찾을 수 없습니다."));
        // 이미 좋아요되어있으면 에러 반환
        if (boardRepository.findByUserAndBoard(user, board).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Board like = Board.builder()
                .board(board)
                .user(user)
                .build();

        boardRepository.save(like);
        //boardRepository.updateCount(board,true); //2번째 매개변수가 true 라면 +1 false 라면 -1 입니다.
    }


    public void likeDelete(BoardRequestDto.LikeDto likeDto){
        User user = userRepository.findById(likeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("유저 정보를 찾을 수 없습니다."));

        Board board = boardRepository.findById(likeDto.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글 정보를 찾을 수 없습니다."));

        //Board like = boardRepository.findByUserAndBoard(user, board)
        //        .orElseThrow(() -> new RuntimeException("좋아요 정보를 찾을 수 없습니다."));

        //boardRepository.delete(like);
        //boardRepository.updateCount(board,true);
        }

    }*/

}







