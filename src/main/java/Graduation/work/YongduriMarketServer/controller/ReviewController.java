package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ReviewRequestDto;
import Graduation.work.YongduriMarketServer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List> reviewList(@AuthenticationPrincipal CustomUserDetails user) throws Exception{
        return new ResponseEntity<List>(reviewService.ReviewList(user.getStudentId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> reviewWrite(@AuthenticationPrincipal CustomUserDetails user, ReviewRequestDto.ReviewWriteDto request) throws Exception{
        reviewService.reviewWrite(user.getStudentId(), request);
        return ResponseEntity.ok().build();
    }
}
