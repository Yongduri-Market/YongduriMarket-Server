package Graduation.work.YongduriMarketServer.trade.service;


import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.product.entity.Product;
import Graduation.work.YongduriMarketServer.product.entity.ProductSortType;
import Graduation.work.YongduriMarketServer.product.service.ProductService;
import Graduation.work.YongduriMarketServer.service.UserService;
import Graduation.work.YongduriMarketServer.trade.entity.Trade;
import Graduation.work.YongduriMarketServer.trade.repository.TradeRepository;
import Graduation.work.YongduriMarketServer.trade.request.TradeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeService {
    private final FileService fileService;
    private final UserService userService;
    private final TradeRepository tradeRepository;
    private final ProductService productService;

    @Transactional
    public Trade save(TradeRequest request) {
        final Trade trade = TradeRequest.of(request);

        final User user1 = userService.findByStudentId(request.getBuyerId());
        final User user2 = userService.findByStudentId(request.getSellerId());

        final Product product = productService.getById(request.getProductId());

        trade.setBuyer(user1);
        trade.setSeller(user2);
        trade.setProduct(product);

        return tradeRepository.save(trade);
    }

    @Transactional
    public Trade patch(Long tradeId, TradeRequest request) {
        final Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 거래"));

        final User user = userService.findByStudentId(request.getSellerId());
        final Product product = productService.getById(request.getProductId());

        trade.update(user, product, request.getStatus(), request.getLocation());

        return tradeRepository.save(trade);
    }

    public List<Trade> getAll() {
        return tradeRepository.findAll();
    }

    public Trade getById(Long tradeId) {
        return tradeRepository.findById(tradeId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    public List<Trade> findAllByBuyerIdAndSellerId(Long buyerId, Long sellerId) {
        return tradeRepository.findAllByBuyerIdAndSellerId(buyerId, sellerId);
    }

    @Transactional
    public void deleteById(Long tradeId) {
        tradeRepository.deleteById(tradeId);
    }

}
