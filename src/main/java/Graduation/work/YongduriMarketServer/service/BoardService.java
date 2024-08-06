package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
//import Graduation.work.YongduriMarketServer.dto.BoardResponseSavedIdDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
//import com.google.api.gax.rpc.NotFoundException;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시글 전체 조회
    @Transactional
    public List<Board> boardList() {
        try {
            List<Board> boards = boardRepository.findAll();
            return boards;

        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시글 단건 조회
    @Transactional(readOnly = true)
    public BoardResponseDto boardDetail(BoardRequestDto.DetailDto request) throws Exception {
        Board board = boardRepository.findByBoardId(request.getBoardId()).orElseThrow(() -> {
            throw new CustomException(ErrorCode.NOT_EXIST_ID);
        });
        try {
            BoardResponseDto response = BoardResponseDto.GetBoardDetailDto(board);
            return response;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    // 게시글 작성
    @Transactional
    public void boardWrite(Long studentId, BoardRequestDto.BoardWriteDto requestDto) throws Exception{
        try {
            User user = findByStudentId(studentId);

            Board board = Board.builder()
                    .user(user)
                    .tradePlace(requestDto.getTradePlace())
                    .tradeMethod(requestDto.getTradeMethod())
                    .status(requestDto.getStatus())
                    .salesCategory(requestDto.getSalesCategory())
                    .boardTitle(requestDto.getBoardTitle())
                    .boardContent(requestDto.getBoardContent())
                    .hits(requestDto.getHits())
                    .price(requestDto.getPrice())
                    .createdAt(LocalDateTime.now())
                    .build();
            boardRepository.save(board);
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    //게시글 수정
    @Transactional
    public void boardEdit(long studentId, BoardRequestDto.BoardEditDto requestDto)throws Exception{
        User user = findByStudentId(studentId);
        //1. 기존 게시글 꺼내와서
        Optional<Board> board = boardRepository.findById(requestDto.getBoardId());

        //존재하지 않는 게시글
        if(board.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_EXIST_ID);
        }

        Board existBoard = board.get();

        //권한 없음
        if(!existBoard.getUser().equals(user)) {
            throw new CustomException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }

        //2. 게시글에 boardUpdate 덮어씌우기
        try{
            existBoard.setTradePlace(requestDto.getTradePlace());
            existBoard.setTradeMethod(requestDto.getTradeMethod());
            existBoard.setStatus(requestDto.getStatus());
            existBoard.setSalesCategory(requestDto.getSalesCategory());
            existBoard.setBoardTitle(requestDto.getBoardTitle());
            existBoard.setBoardContent(requestDto.getBoardContent());
            existBoard.setPrice(requestDto.getPrice());
            existBoard.setUpdatedAt(LocalDateTime.now());
            boardRepository.save(existBoard);
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //게시글 삭제
    @Transactional
    public void boardDelete(long studentId, BoardRequestDto.boardIdDto requestDto) throws Exception{
        User user = findByStudentId(studentId);
        Board board = findByBoardId(requestDto.getBoardId());
        Optional<Board> opBoard = boardRepository.findById(requestDto.getBoardId());

        // 404
        if(opBoard.isEmpty()) throw new CustomException(ErrorCode.NOT_EXIST_ID);
        // 403
        if(!opBoard.get().getUser().equals(user)) throw new CustomException(ErrorCode.ACCESS_TOKEN_EXPIRED);

        try{
            boardRepository.delete(board);
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }



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

    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
    public Board findByBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
}