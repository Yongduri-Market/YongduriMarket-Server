package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;

    private Long boardId;

    private Long revieweeId;

    private Long reviewerId;

    private Integer assessment;

    private Integer scope;

    private Integer reviewStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ReviewResponseDto getReviewDto(Review review){
        return new ReviewResponseDto(
                review.getReviewId(),
                review.getBoard().getBoardId(),

                review.getReviewee().getStudentId(),
                review.getReviewer().getStudentId(),
                review.getAssessment(),
                review.getScope(),
                review.getReviewStatus(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

}
