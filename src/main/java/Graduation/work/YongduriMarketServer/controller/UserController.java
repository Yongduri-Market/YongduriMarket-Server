package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.dto.UserResponseDto;
import Graduation.work.YongduriMarketServer.service.BoardService;
import Graduation.work.YongduriMarketServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 내 정보 조회
    @GetMapping
    public ResponseEntity<UserResponseDto>getInfoList(@AuthenticationPrincipal CustomUserDetails user)throws Exception {
        return new ResponseEntity<UserResponseDto>(userService.getInfoList(user.getStudentId()), HttpStatus.OK);
    }

    // 내 정보 수정
    @PutMapping
    public ResponseEntity<Boolean>infoUpdate(@AuthenticationPrincipal CustomUserDetails user,UserResponseDto request)throws Exception {
        return new ResponseEntity<Boolean>(userService.infoUpdate(user.getStudentId(), request), HttpStatus.OK);
    }
    // 좋아요 목록 조회
    @GetMapping("/likeList")
    public ResponseEntity<List<BoardResponseDto>> getLikedBoards(@AuthenticationPrincipal CustomUserDetails user) throws Exception {
        return new ResponseEntity<>(userService.getLikedBoards(user.getStudentId()), HttpStatus.OK);
    }

}
