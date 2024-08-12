package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ChatRoomRequestDto;
import Graduation.work.YongduriMarketServer.dto.ChatRoomResponseDto;
import Graduation.work.YongduriMarketServer.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/chatRoom")
public class ChatRoomController {

    public final ChatRoomService chatRoomService;

    //전체조회
    @GetMapping
    public ResponseEntity<List> getList() throws Exception{
        return new ResponseEntity<List>(chatRoomService.getListAll(), HttpStatus.OK);
    }

    //상세조회
    @GetMapping("/detail")
    public ResponseEntity<ChatRoomResponseDto> getDetail(ChatRoomRequestDto.DetailDto request) throws Exception{
        return new ResponseEntity<ChatRoomResponseDto>(chatRoomService.getDetail(request), HttpStatus.OK);
    }
    //방 생성
    @PostMapping
    public ResponseEntity<Boolean> create(@AuthenticationPrincipal CustomUserDetails user, ChatRoomRequestDto.CreateDto request) throws Exception{
        return new ResponseEntity<Boolean>(chatRoomService.create(user.getStudentId(),request), HttpStatus.OK);
    }
    // 방 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> delete(@AuthenticationPrincipal CustomUserDetails user,ChatRoomRequestDto.DeleteDto request) throws Exception{
        return new ResponseEntity<Boolean>(chatRoomService.delete(user.getStudentId(),request), HttpStatus.OK);
    }

    //거래 종료
    @PutMapping("/end")
    public ResponseEntity<Boolean> endTrade(@AuthenticationPrincipal CustomUserDetails user, ChatRoomRequestDto.EndTradeDto request) throws Exception{
        return new ResponseEntity<Boolean>(chatRoomService.endTrade(user.getStudentId(),request), HttpStatus.OK);
    }
    //거래 예약
    @PutMapping("/reserve")
    public ResponseEntity<Boolean> reserveTrade(@AuthenticationPrincipal CustomUserDetails user,ChatRoomRequestDto.reserveTradeDto request) throws Exception{
        return new ResponseEntity<Boolean>(chatRoomService.reserveTrade(user.getStudentId(),request), HttpStatus.OK);
    }



}
