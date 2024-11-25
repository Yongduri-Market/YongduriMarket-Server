package Graduation.work.YongduriMarketServer.product.dto.request;


import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.domain.state.TradeMethodType;
import Graduation.work.YongduriMarketServer.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @NotNull
    private Long price;
    private SalesStatus salesStatus;
    private SalesType salesType;
    private TradeMethodType tradeMethodType;
    private Long salesId;
    private MultipartFile file;

    public static Product of(ProductRequest request) {
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setBody(request.getBody());
        product.setPrice(request.getPrice());
        product.setSalesStatus(request.getSalesStatus());
        product.setSalesType(request.getSalesType());
        product.setTradeMethodType(request.getTradeMethodType());

        return product;
    }
}
