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
    public static class DetailDto {
        private Long boardId;
    }


    @Getter
    @Setter
    public static class CreateDto {
        private Integer method;
        private Integer sales;
        private String title;
        private String content;
        private Integer price;



    }


    @Getter
    @Setter
    public  static class UpdateDto {
        private Long boardId;
        private Integer method;
        private Integer sales;
        private String title;
        private String content;
        private Integer price;
    }



    @Getter
    @Setter
    public static class DeleteDto {
        private Long boardId;
    }



    @Getter
    @Setter
    public static class LikeDto {
        private Long boardId;


    }
    @Getter
    @Setter
    public static class UnLikeDto {
        private Long boardId;
    }



    @Getter
    @Setter
    public static class ReserveTradeDto {
        private Long boardId;

    }
    @Getter
    @Setter
    public static class EndTradeDto {
        private Long boardId;
    }



}