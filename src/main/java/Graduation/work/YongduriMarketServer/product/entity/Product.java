package Graduation.work.YongduriMarketServer.product.entity;

import Graduation.work.YongduriMarketServer.domain.Base;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private Long price;
    private SalesStatus salesStatus;
    private SalesType salesType;
    private TradeMethodType tradeMethodType;

    @OneToOne
    @JoinColumn(name = "sales_id")
    private User user;


    @OneToOne
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    public void update(String title, String body, Long price, SalesStatus salesStatus, SalesType salesType, TradeMethodType tradeMethodType) {
        this.title = title;
        this.body = body;
        this.price = price;
        this.salesStatus = salesStatus;
        this.salesType = salesType;
        this.tradeMethodType = tradeMethodType;
    }
}
