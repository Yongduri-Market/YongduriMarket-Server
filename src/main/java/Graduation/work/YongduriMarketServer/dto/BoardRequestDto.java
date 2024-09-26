package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradePlaceType;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import lombok.*;

import java.time.LocalDateTime;


public class BoardRequestDto {

    @Getter
    @Setter
    public class DetailDto {
        private Long boardId;
    }


    @Getter
    @Setter
    public class CreateDto {
        private Integer place;
        private Integer method;
        private Integer sales;
        private String boardTitle;
        private String boardContent;
        private Integer price;



    }


    @Getter
    @Setter
    public class UpdateDto {
        private Long boardId;
        private Integer place;
        private Integer method;
        private Integer sales;
        private String boardTitle;
        private String boardContent;
        private Integer price;
    }



    @Getter
    @Setter
    public class DeleteDto {
        private Long boardId;
    }



    @Getter
    @Setter
    public class LikeDto {
        private Long boardId;


    }
    @Getter
    @Setter
    public class UnLikeDto {
        private Long boardId;
    }



    @Getter
    @Setter
    public class ReserveTradeDto {
        private Long boardId;

    }
    @Getter
    @Setter
    public class EndTradeDto {
        private Long boardId;
    }



}