package Graduation.work.YongduriMarketServer.trade.controller;

import Graduation.work.YongduriMarketServer.trade.request.TradeRequest;
import Graduation.work.YongduriMarketServer.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;

    /**
     * 거래 저장
     * @param tradeRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TradeRequest tradeRequest) {
        return ResponseEntity.ok(tradeService.save(tradeRequest));
    }

    /**
     * 거래 수정
     * @param tradeRequest
     * @param tradeId
     * @return
     */
    @PatchMapping("/{tradeId}")
    public ResponseEntity<?> patch(@RequestBody TradeRequest tradeRequest,
                                   @PathVariable(value = "tradeId") Long tradeId) {

        log.info("request[{}]", tradeRequest);

        return ResponseEntity.ok(tradeService.patch(tradeId, tradeRequest));
    }

    /**
     * 거래 상세 조회
     * @param tradeId
     * @return
     */
    @GetMapping("/{tradeId}")
    public ResponseEntity<?> getById(@PathVariable(value = "tradeId") Long tradeId) {
        log.info("거래 상세조회[{}]", tradeId);
        return ResponseEntity.ok(tradeService.getById(tradeId));
    }

    /**
     * 구매/판매자 조회
     * @param buyerId
     * @param sellerId
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<?> getListByUserId(@RequestParam(value = "buyerId",required = false) Long buyerId,
                                             @RequestParam(value = "sellerId",required = false) Long sellerId) {
        return ResponseEntity.ok(tradeService.findAllByBuyerIdAndSellerId(buyerId, sellerId));
    }

    /**
     * 전체 거래 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getTradeList() {
        return ResponseEntity.ok(tradeService.getAll());
    }

    /**
     * 거래 삭제
     * @param tradeId
     * @return
     */
    @DeleteMapping("/{tradeId}")
    public ResponseEntity<?> delete(@PathVariable(value = "tradeId") Long tradeId) {
        tradeService.deleteById(tradeId);
        return ResponseEntity.ok().build();
    }
}
