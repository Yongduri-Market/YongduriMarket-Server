package Graduation.work.YongduriMarketServer.trade.entity;

import Graduation.work.YongduriMarketServer.domain.Base;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import Graduation.work.YongduriMarketServer.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Trade extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key references to buyer (user)
    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "studentId")
    private User buyer;

    // Foreign key references to seller (user)
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "studentId")
    private User seller;

    // Foreign key references to product
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private TradeStatus status;
    private String location;

    public void update(User seller, Product product, TradeStatus status, String location) {
        this.seller = seller;
        this.product = product;
        this.status = status;
        this.location = location;
    }
}
