package Graduation.work.YongduriMarketServer.controller;


import Graduation.work.YongduriMarketServer.dto.*;
import Graduation.work.YongduriMarketServer.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;

<<<<<<< HEAD
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
=======
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

@RequiredArgsConstructor
@RestController
public class MainController {
    public final MainService mainService;

<<<<<<< HEAD
    @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> join(@RequestPart("user") JoinDto request, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception{
        return new ResponseEntity<Boolean>(mainService.join(request, file), HttpStatus.OK);
=======
    @PostMapping(value = "/join")
    public ResponseEntity<Boolean> join(@RequestBody JoinDto request) throws Exception{
        return new ResponseEntity<Boolean>(mainService.join(request), HttpStatus.OK);
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) throws Exception{
        return new ResponseEntity<LoginResponseDto>(mainService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/join/email")
<<<<<<< HEAD
    public ResponseEntity<String>joinEmail(EmailRequestDto request) throws  Exception{
=======
    public ResponseEntity<String>joinEmail(@RequestBody EmailRequestDto request) throws  Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        System.out.println("joinEmail request" + request);
        return new ResponseEntity<String>(mainService.joinEmail(request.getEmail()), HttpStatus.OK);
    }

    @PostMapping(value = "/password/email")
<<<<<<< HEAD
    public ResponseEntity<String>pwdEmail(EmailRequestDto request) throws  Exception{
=======
    public ResponseEntity<String>pwdEmail(@RequestBody EmailRequestDto request) throws  Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        System.out.println("pwdEmail request" + request);
        return new ResponseEntity<String>(mainService.pwdEmail(request.getEmail()), HttpStatus.OK);
    }
    @PostMapping(value = "/password/change")
<<<<<<< HEAD
    public ResponseEntity<Boolean> pwdChange(PwdChangeRequestDto request) throws  Exception{
=======
    public ResponseEntity<Boolean> pwdChange(@RequestBody PwdChangeRequestDto request) throws  Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        System.out.println("pwdChange request" + request);
        return new ResponseEntity<Boolean>(mainService.pwdChange(request), HttpStatus.OK);
    }
    @PostMapping(value = "/nickname/check")
<<<<<<< HEAD
    public ResponseEntity<Boolean> nicknameCheck(NicknameCheckRequestDto request) throws  Exception{
=======
    public ResponseEntity<Boolean> nicknameCheck(@RequestBody NicknameCheckRequestDto request) throws  Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        System.out.println("nicknameCheck request" + request);
        return new ResponseEntity<Boolean>(mainService.nicknameCheck(request), HttpStatus.OK);
    }
    @PostMapping(value = "/accessToken/reissue")
<<<<<<< HEAD
    public ResponseEntity<TokenDto>  createNewAccessToken(TokenDto tokenDto) throws  Exception{
=======
    public ResponseEntity<TokenDto>  createNewAccessToken(@RequestBody TokenDto tokenDto) throws  Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        return new ResponseEntity<>(mainService.refreshAccessToken(tokenDto), HttpStatus.OK);
    }
}
