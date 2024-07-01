package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.swing.plaf.nimbus.State;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<List> boardList(){
        return new ResponseEntity<List>(boardService.boardList(), HttpStatus.OK);
    }
    //게시글 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<BoardResponseDto> boardDetail(BoardRequestDto.DetailDto request)  throws Exception{
        return new ResponseEntity<BoardResponseDto>(boardService.boardDetail(request), HttpStatus.OK);
    }
    //게시글 등록
    @PostMapping("/write")
    public ResponseEntity<?> boardWrite(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.BoardWriteDto request) throws Exception {
        boardService.boardWrite(user.getStudentId(), request);
        return ResponseEntity.ok().build();
    }
    // 게시글 수정
    @PutMapping("/edit")
    public ResponseEntity<?> boardEdit(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.BoardEditDto request) throws Exception{
        boardService.boardEdit(user.getStudentId(), request);
        return ResponseEntity.ok().build();
    }
    //게시글 삭제
    @DeleteMapping
    public ResponseEntity<?> boardDelete(@AuthenticationPrincipal CustomUserDetails user, BoardRequestDto.boardIdDto request)throws Exception{
        boardService.boardDelete(user.getStudentId(), request);
        return ResponseEntity.ok().build();
    }

    /*
    //좋아요 등록
    @PostMapping
    public ResponseEntity<?> likeInsert(@RequestBody @Validated BoardRequestDto.LikeDto likeDto){
        boardService.likeInsert(likeDto);
        return new ResponseEntity<>("like insert complete", HttpStatus.OK);
    }
    //좋아요 취소
    @DeleteMapping
    public ResponseEntity<?> likeDelete(@RequestBody @Validated BoardRequestDto.LikeDto likeDto){
        boardService.likeDelete(likeDto);
        return new ResponseEntity<>("like delete complete", HttpStatus.OK);
    }
    */


}