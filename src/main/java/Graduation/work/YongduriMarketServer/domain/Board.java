package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradePlaceType;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
<<<<<<< HEAD
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
<<<<<<< HEAD
import org.hibernate.annotations.ManyToAny;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long boardId;

    @JoinColumn(name = "studeint_id")
    @ManyToOne
    private User userId;

<<<<<<< HEAD

=======
/*
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    //거래 장소
    //인성관(0) 문예대(1) 체과대(2) 종합체육관(4) 도서관(5) 에융대(6) 보1(7) 보2(8) 무도대(9) 대운동장(10)
    @Column
    private TradePlaceType place;

<<<<<<< HEAD
=======
    */

>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    //거래 방식
    //판매하기(0) 나눔하기(1) 구해요(2)
    @Column
    private TradeMethodType method;

    //거래 상태
    //판매중(0) 예약중(1) 거래완료(2)
    @Column
    private TradeStatus status;

    //판매 카테고리
<<<<<<< HEAD
    //책(0), 의류(1), 전자기기(2), 부동산(3), 기타(4);
=======
    //책(0), 의류(1), 전자기기(2), 부동산(3), 기타(4)
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    @Column
    private SalesType sales;

    @Column
<<<<<<< HEAD
    private String boardTitle;

    @Column(columnDefinition = "TEXT")
    private String boardContent;
=======
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f


    @Column(nullable = false)
    private Integer price;

    //좋아요
    // 0 좋아요 취소 1 좋아요
    @Column
    @Builder.Default
    private Integer likeCount = 0;

<<<<<<< HEAD
    @Column
    private String fileInfoId;

=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;



}