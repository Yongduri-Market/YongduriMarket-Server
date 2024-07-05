package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDto {
    private Long reviewId;

    private Long board_Id;

    private Long reviewee_Id;

    private Long reviewer_Id;

    private Integer assessment;

    private Integer scope;

    private Integer reviewStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ReviewResponseDto GetReviewDetailDto(Review review){
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
