package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<List> getAllBoards()throws Exception {
        return new ResponseEntity<List>(boardService.getAllBoards(), HttpStatus.OK);
    }
    //게시글 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<BoardResponseDto> getBoardDetail(@RequestParam Long boardId)  throws Exception{
        return new ResponseEntity<BoardResponseDto>(boardService.getBoardDetail(boardId), HttpStatus.OK);
    }
    //게시글 등록
    @PostMapping
    public ResponseEntity<Boolean> createBoard(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.CreateDto request) throws Exception {
        return new ResponseEntity<Boolean>(boardService.createBoard(user.getStudentId(),request), HttpStatus.OK);
    }
    // 게시글 수정
    @PutMapping
    public ResponseEntity<Boolean> updateBoard(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.UpdateDto request) throws Exception{
        return new ResponseEntity<Boolean>(boardService.updateBoard(user.getStudentId(),request), HttpStatus.OK);
    }
    //게시글 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> deleteBoard(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.DeleteDto request)throws Exception{
        return new ResponseEntity<Boolean>(boardService.deleteBoard(user.getStudentId(),request), HttpStatus.OK);
    }


    //게시글 좋아요
    @PutMapping("/like")
    public ResponseEntity<Boolean> likeBoard(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.LikeDto request) throws Exception {

        System.out.println("Received LikeDto: " + request); // DTO 객체 출력
        System.out.println("Board ID: " + request.getBoardId()); // Board ID 확인
        return new ResponseEntity<Boolean>(boardService.likeBoard(user.getStudentId(),request), HttpStatus.OK);
    }

    //게시글 좋아요 해제
    @PutMapping("/unlike")
    public ResponseEntity<Boolean> unlikeBoard(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.UnLikeDto request) throws Exception {
        return new ResponseEntity<Boolean>(boardService.unlikeBoard(user.getStudentId(),request), HttpStatus.OK);
    }



}