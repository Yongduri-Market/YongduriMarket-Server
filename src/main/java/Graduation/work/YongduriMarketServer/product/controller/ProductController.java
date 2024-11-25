package Graduation.work.YongduriMarketServer.product.controller;

import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.product.dto.request.ProductRequest;
import Graduation.work.YongduriMarketServer.product.entity.ProductSortType;
import Graduation.work.YongduriMarketServer.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart(value = "productRequest") ProductRequest productRequest) {
        productRequest.setFile(file);
        return ResponseEntity.ok(productService.save(productRequest));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> patch(@RequestPart(value = "productRequest") ProductRequest productRequest,
                                   @PathVariable(value = "productId") Long productId) {

        log.info("request[{}]", productRequest);

        return ResponseEntity.ok(productService.patch(productId, productRequest));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable(value = "productId") Long productId) {
        log.info("상품 상세조회[{}]", productId);
        return ResponseEntity.ok(productService.getById(productId));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getListByUserId(@RequestParam(value = "salesId") Long salesId) {
        log.info("상품 상세조회[{}]", salesId);
        return ResponseEntity.ok(productService.findProductBySalesId(salesId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@PathVariable(value = "productId") Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> getBoardList(@RequestParam(required = false, value = "sort") ProductSortType sort,
                                          @RequestParam(required = false, value = "isReserved") SalesStatus isReserved) {

        if (sort == null && isReserved == null) {
            return ResponseEntity.ok(productService.getAll());
        } else if (sort == null && isReserved != null) {
            return ResponseEntity.ok(productService.getByReserveStatus(isReserved));
        } else if (sort != null && isReserved == null) {
            return ResponseEntity.ok(productService.getAllBySort(sort));
        } else if (sort != null && isReserved != null) {
            return ResponseEntity.ok(productService.getAllBySortAndSalesStatus(sort, isReserved));
        }
        return ResponseEntity.ok().build();
    }
}
