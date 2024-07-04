package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    //유저랑 연결하고 ,, 처리
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "userId", updatable = false)
    private User user;

    //????? 이넘 어케하징 찾아보자
    @Column(nullable = false)
    private Long tradePlace;
    @Column(nullable = false)
    private Long tradeMethod;
    @Column(nullable = false)
    private Long tradeStatus;
    @Column(nullable = false)
    private Long salesCategory;

    @Column(nullable = false, length = 20)
    private String boardTitle;

    @Column(nullable = false, length = 150)
    private String boardContent;

    @Column//(nullable = false)
    private Long hits;

    @Column(nullable = false)
    private Long price;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    @Builder
    public Board(User userId, Long tradePlace, Long tradeMethod, Long tradeStatus, Long salesCategory, String boardTitle, String boardContent, Long hits, Long price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = userId;
        this.tradePlace = tradePlace;
        this.tradeMethod = tradeMethod;
        this.tradeStatus = tradeStatus;
        this.salesCategory = salesCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.hits = hits;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

// board 값만 수정해 주고 boardRepository를 이용해서 저장하는 로직 -> JPA의 Dirty Checking(변경 감지) 때문
    public void update(BoardRequestDto requestDto) {
        this.tradePlace = requestDto.getTradePlace();
        this.tradeMethod = requestDto.getTradeMethod();
        this.tradeStatus = requestDto.getTradeStatus();
        this.salesCategory = requestDto.getSalesCategory();
        this.boardTitle = requestDto.getBoardTitle();
        this.boardContent = requestDto.getBoardContent();
        this.price = requestDto.getPrice();
        this.updatedAt = LocalDateTime.now();
    }
    //조회수
    public Board updateHits(Long hits) {
        this.hits = hits + 1;
        return this;
    }
}