package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.dto.BoardResponseSavedIdDto;
import Graduation.work.YongduriMarketServer.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping //게시글 전체 조회
    public ResponseEntity<List> boardList(){
        return new ResponseEntity<List>(boardService.boardList(), HttpStatus.OK);
    }

    @GetMapping("/detail/{boardId}") //게시글 상세 조회
    public ResponseEntity<BoardResponseDto> boardDetail(@PathVariable("boardId") Long boardId) {
        BoardResponseDto boardResponseDto = boardService.boardDetail(boardId);
        return ResponseEntity.ok(boardResponseDto);
    }

    //게시글 등록
    @PostMapping("/write")
    public ResponseEntity<BoardResponseSavedIdDto> boardWrite(@RequestBody BoardRequestDto requestDto){
        BoardResponseSavedIdDto boardResponseSavedIdDto = boardService.boardWrite(requestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{savedId}")
                .buildAndExpand(boardResponseSavedIdDto.getSavedId())
                .toUri();
        return ResponseEntity.created(location).body(boardResponseSavedIdDto);
    }

    // 게시글 수정
    @PutMapping
    public ResponseEntity<?> boardEdit(@PathVariable("boardId") long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        boardService.boardEdit(boardId, boardRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> boardDelete(@PathVariable long boardId){
        boardService.boardDelete(boardId);
        return new ResponseEntity<>("board delete complete", HttpStatus.OK);
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
    //게시글 작성
    //게시글 삭제
    //게시글 수정
    //게시글 좋아요
    //게시글 좋아요 취소


}
