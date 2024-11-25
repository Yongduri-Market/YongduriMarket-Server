package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradePlaceType;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import lombok.*;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
=======

import java.time.LocalDateTime;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f


public class BoardRequestDto {

    @Getter
    @Setter
<<<<<<< HEAD
    public class DetailDto {
=======
    public static class DetailDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;
    }


    @Getter
    @Setter
    public static class CreateDto {
<<<<<<< HEAD
        private Integer place;
        private Integer method;
        private Integer sales;
        private String boardTitle;
        private String boardContent;
        private Integer price;

=======
        private Integer method;
        private Integer sales;
        private String title;
        private String content;
        private Integer price;



>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
    }


    @Getter
    @Setter
<<<<<<< HEAD
    public class UpdateDto {
        private Long boardId;
        private Integer place;
        private Integer method;
        private Integer sales;
        private String boardTitle;
        private String boardContent;
=======
    public  static class UpdateDto {
        private Long boardId;
        private Integer method;
        private Integer sales;
        private String title;
        private String content;
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Integer price;
    }



    @Getter
    @Setter
<<<<<<< HEAD
    public class DeleteDto {
=======
    public static class DeleteDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;
    }



    @Getter
    @Setter
<<<<<<< HEAD
    public class LikeDto {
=======
    public static class LikeDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;


    }
    @Getter
    @Setter
<<<<<<< HEAD
    public class UnLikeDto {
=======
    public static class UnLikeDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;
    }



    @Getter
    @Setter
<<<<<<< HEAD
    public class ReserveTradeDto {
=======
    public static class ReserveTradeDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;

    }
    @Getter
    @Setter
<<<<<<< HEAD
    public class EndTradeDto {
=======
    public static class EndTradeDto {
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
        private Long boardId;
    }



}