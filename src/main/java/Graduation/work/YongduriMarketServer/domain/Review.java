package Graduation.work.YongduriMarketServer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long reviewId;

    @JoinColumn(name = "board_Id")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "reviewee_Id",nullable = false)
    @ManyToOne
    private User reviewee;

    @JoinColumn(name = "reviewer_Id",nullable = false)
    @ManyToOne
    private User reviewer;

    // 평가  1~4
    //1 : 제가 있는 곳까지 와서 거래했어요.
    //2 : 시간 약속을 잘 지켜요
    //3 : 친절하고 매너가 좋아요
    //4 : 응답이 빨라요
    @Column
    private Integer assessment;

    // 별점 1~5
    @Column
    private Integer scope;


    // 0 작성 안 함, 1 작성 함
    @Column
    private Integer reviewStatus;



    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;
}
