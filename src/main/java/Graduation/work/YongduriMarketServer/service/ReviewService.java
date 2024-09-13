package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.Review;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.ReviewRequestDto;
import Graduation.work.YongduriMarketServer.dto.ReviewResponseDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.ReviewRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //리뷰 조회
    public List <ReviewResponseDto> getReview() throws Exception {
        List<Review> review = reviewRepository.findByOrderByCreatedAtDesc();
        List<ReviewResponseDto> getAllListDto = new ArrayList<>();
        review.forEach(s-> getAllListDto.add(ReviewResponseDto.getReviewDto(s)));
        return getAllListDto;
    }

    //리뷰 작성
    public Boolean writeReview(Long studentId, ReviewRequestDto.ReviewWriteDto request) {
        User reviewer = findByStudentId(studentId);
        User reviewee = findByStudentId(studentId);

        Board board = findByBoardId(request.getBoardId());
        if(request.getBoardId() == null ||request.getRoomId() ==null || request.getRevieweeId() ==null || request.getAssessment() ==null ||
        request.getScope() == null || request.getReviewStatus() == null){
          throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{
            Review review = Review.builder()
                    .board(board)
                    .reviewee(reviewee)
                    .reviewer(reviewer)
                    .assessment(request.getAssessment())
                    .scope(request.getScope())
                    .reviewStatus(request.getReviewStatus())
                    .build();
            reviewRepository.save(review);
            return true;
        }catch(Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
    public Board findByBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_ID));
    }



}
