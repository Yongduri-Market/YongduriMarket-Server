package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ReviewRequestDto;
import Graduation.work.YongduriMarketServer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List> getReview() throws Exception{
        return new ResponseEntity<List>(reviewService.getReview(), HttpStatus.OK);
    }

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<Boolean> writeReview(@AuthenticationPrincipal CustomUserDetails user, @RequestBody ReviewRequestDto.ReviewWriteDto request) throws Exception{
=======
    public ResponseEntity<Boolean> writeReview(@AuthenticationPrincipal CustomUserDetails user, ReviewRequestDto.ReviewWriteDto request) throws Exception{
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        return new ResponseEntity<Boolean>(reviewService.writeReview(user.getStudentId(),request), HttpStatus.OK);
    }
}
