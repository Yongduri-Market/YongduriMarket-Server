package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.Review;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.ReviewRequestDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.ReviewRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //리뷰 조회
    @Transactional(readOnly = true)
    public List<Review> ReviewList(long studentId) throws Exception {
        try{
            User user = findByStudentId(studentId);
            List<Review> reviews = reviewRepository.findAllById((Iterable<Long>) user);
            return reviews;
        }catch (Exception e){
                throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
            }
    }
    //리뷰 작성
    @Transactional
    public void reviewWrite(Long studentId, ReviewRequestDto.ReviewWriteDto requestDto) throws Exception{
        //400
        if(requestDto.getReviewId() == null) throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        //403

        try {
            User reviewer = findByStudentId(studentId);
            User reviewee = findByStudentId(requestDto.getReviewee_Id());
            Board board = findByBoardId(requestDto.getBoard_Id());
            Review review = Review.builder()
                    .board(board)
                    .reviewee(reviewee)
                    .reviewer(reviewer)
                    .assessment(requestDto.getAssessment())
                    .scope(requestDto.getScope())
                    .reviewStatus(requestDto.getReviewStatus())
                    .createdAt(LocalDateTime.now())
                    .build();
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
    public Board findByBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
}
