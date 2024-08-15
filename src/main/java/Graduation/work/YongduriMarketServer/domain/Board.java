package Graduation.work.YongduriMarketServer.domain;

import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.ConnectionBuilder;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "studentId", updatable = false)
    private User user;

    @Column(nullable = false)
    private Long tradePlace;

    @Column(nullable = false)
    private Long tradeMethod;

    @Column(nullable = false)
    private TradeStatus status;

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

    //조회수
    public Board updateHits(Long hits) {
        this.hits = hits + 1;
        return this;
    }


}