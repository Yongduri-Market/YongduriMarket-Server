package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.SearchRequestDto;
import Graduation.work.YongduriMarketServer.dto.SearchResponseDto;
import Graduation.work.YongduriMarketServer.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/search")
public class SearchController {
    private final SearchService searchService;

    //검색어 전체 조회
    @GetMapping
    public ResponseEntity<List> getAllKeyword() throws Exception{
        return new ResponseEntity<List>(searchService.getAllKeyword(), HttpStatus.OK);
    }

    //관련 키워드 조회
    @GetMapping(value = "/keyword")
    public ResponseEntity<List<SearchResponseDto>> getKeyword(@AuthenticationPrincipal CustomUserDetails user,@RequestBody SearchRequestDto.CheckDto request) throws Exception{
        return new ResponseEntity<List<SearchResponseDto>>(searchService.getKeyword(user.getStudentId(),request), HttpStatus.OK);
    }


    //검색어 등록
    @PostMapping
    public ResponseEntity<Boolean> registerKeyword(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SearchRequestDto.RegisterDto request) throws Exception{
        return new ResponseEntity<Boolean>(searchService.registerKeyword(user.getStudentId(),request), HttpStatus.OK);
    }



    //검색어 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> deleteKeyword(@AuthenticationPrincipal CustomUserDetails user, @RequestBody SearchRequestDto.DeleteDto request) throws Exception{
        return new ResponseEntity<Boolean>(searchService.deleteKeyword(user.getStudentId(),request), HttpStatus.OK);
    }






}
