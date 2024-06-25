package Graduation.work.YongduriMarketServer.domain;

import jakarta.persistence.*;

public class ChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roomId;

    @JoinColumn(name = "board_Id",nullable = false)
    @ManyToOne
    private Board board;

    @Column(nullable = false)
    private String reportContents;
}
