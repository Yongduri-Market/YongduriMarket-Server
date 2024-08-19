package Graduation.work.YongduriMarketServer.controller;


import Graduation.work.YongduriMarketServer.dto.*;
import Graduation.work.YongduriMarketServer.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    public final MainService mainService;

    @PostMapping(value = "/join")
    public ResponseEntity<Boolean> join(JoinDto request) throws Exception{
        return new ResponseEntity<Boolean>(mainService.join(request), HttpStatus.OK);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto request) throws Exception{
        return new ResponseEntity<LoginResponseDto>(mainService.login(request), HttpStatus.OK);
    }
    @GetMapping(value = "/main")
    public ResponseEntity<Object> getAllMains() throws Exception {
        return new ResponseEntity<>(mainService.getAllMains(), HttpStatus.OK);
    }

    @PostMapping(value = "/join/email")
    public ResponseEntity<String>joinEmail(EmailRequestDto request) throws  Exception{
        System.out.println("joinEmail request" + request);
        return new ResponseEntity<String>(mainService.joinEmail(request.getEmail()), HttpStatus.OK);
    }

    @PostMapping(value = "/password/email")
    public ResponseEntity<String>pwdEmail(EmailRequestDto request) throws  Exception{
        System.out.println("pwdEmail request" + request);
        return new ResponseEntity<String>(mainService.pwdEmail(request.getEmail()), HttpStatus.OK);
    }
    @PostMapping(value = "/password/change")
    public ResponseEntity<Boolean> pwdChange(PwdChangeRequestDto request) throws  Exception{
        System.out.println("pwdChange request" + request);
        return new ResponseEntity<Boolean>(mainService.pwdChange(request), HttpStatus.OK);
    }
    @PostMapping(value = "/nickname/check")
    public ResponseEntity<Boolean> nicknameCheck(NicknameCheckRequestDto request) throws  Exception{
        System.out.println("nicknameCheck request" + request);
        return new ResponseEntity<Boolean>(mainService.nicknameCheck(request), HttpStatus.OK);
    }
    @PostMapping(value = "/accessToken/reissue")
    public ResponseEntity<TokenDto>  createNewAccessToken(TokenDto tokenDto) throws  Exception{
        return new ResponseEntity<>(mainService.refreshAccessToken(tokenDto), HttpStatus.OK);
    }
}
