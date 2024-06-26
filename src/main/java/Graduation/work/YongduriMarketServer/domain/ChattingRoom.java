package Graduation.work.YongduriMarketServer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomId;

    @JoinColumn(name = "board_Id")
    @ManyToOne
    private Board board;

    @JoinColumn(name = "seller_Id")
    @ManyToOne
    private User seller;

    @JoinColumn(name = "buyer_Id")
    @ManyToOne
    private User buyer;


    // 0 채팅 참여함, 1 채팅 참여 안 함
    @Column
    private Integer chattingStatus;

    // 0 거래 미 완료, 1 거래 완료
    @Column
    private Integer TradeStatus;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column
    private LocalDateTime updatedAt;
}
