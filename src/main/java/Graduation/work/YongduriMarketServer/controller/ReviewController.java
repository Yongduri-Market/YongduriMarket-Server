package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ReviewRequestDto;
import Graduation.work.YongduriMarketServer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> writeReview(@AuthenticationPrincipal CustomUserDetails user, @RequestBody ReviewRequestDto.ReviewWriteDto request) throws Exception{
        return new ResponseEntity<Boolean>(reviewService.writeReview(user.getStudentId(),request), HttpStatus.OK);
    }
}
