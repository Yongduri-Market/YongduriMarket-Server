package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradePlaceType;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


public class BoardRequestDto {

    @Getter
    @Setter
    public class DetailDto {
        private Long boardId;
    }


    @Getter
    @Setter
    public static class CreateDto {
        private Integer place;
        private Integer method;
        private Integer sales;
        private String boardTitle;
        private String boardContent;
        private Integer price;

    }


    @Getter
    @Setter
    public static class UpdateDto {
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