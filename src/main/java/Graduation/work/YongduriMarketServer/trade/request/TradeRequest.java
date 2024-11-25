package Graduation.work.YongduriMarketServer.trade.request;


import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.domain.state.TradeStatus;
import Graduation.work.YongduriMarketServer.product.entity.Product;
import Graduation.work.YongduriMarketServer.trade.entity.Trade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TradeRequest {
    @NotNull
    private Long buyerId;
    @NotNull
    private Long sellerId;
    private Long productId;
    private TradeStatus status;
    private String location;

    public static Trade of(TradeRequest request) {
        Trade trade = new Trade();
        trade.setStatus(request.getStatus());
        trade.setLocation(request.getLocation());

        return trade;
    }
}
