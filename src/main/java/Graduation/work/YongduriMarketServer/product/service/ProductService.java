package Graduation.work.YongduriMarketServer.product.service;


import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.domain.state.SalesType;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.product.dto.request.ProductRequest;
import Graduation.work.YongduriMarketServer.product.entity.Product;
import Graduation.work.YongduriMarketServer.product.entity.ProductSortType;
import Graduation.work.YongduriMarketServer.product.repository.ProductRepository;
import Graduation.work.YongduriMarketServer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final FileService fileService;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Transactional
    public Product save(ProductRequest request) {
        final Product product = ProductRequest.of(request);

        if(request.getFile() != null) {
            final List<FileInfo> fileInfos = fileService.saveFile(List.of(request.getFile()), ImageType.PRODUCT);
            product.setFileInfo(fileInfos != null ? fileInfos.get(0) : null);
        }

        final User user = userService.findByStudentId(request.getSalesId());
        product.setUser(user);

        return productRepository.save(product);
    }

    @Transactional
    public Product patch(Long productId, ProductRequest request) {
        final Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));
        product.update(request.getTitle(), request.getBody(), request.getPrice(), request.getSalesStatus(), request.getSalesType(), request.getTradeMethodType());

        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    public List<Product> findProductBySalesId(Long salesId) {

        // sales_id (User의 id)로 User 객체를 조회
        User user = userService.findByStudentId(salesId);

        return productRepository.findByUser(user);
    }

    public List<Product> getByReserveStatus(SalesStatus isReserved) {
        return productRepository.findAllBySalesStatus(isReserved);
    }

    public List<Product> getAllBySort(ProductSortType sort) {
        List<Product> products = productRepository.findAll();

        return sortProducts(sort, products);
    }

    public List<Product> getAllBySortAndSalesStatus(ProductSortType sort, SalesStatus isReserved) {
        List<Product> byReserveStatus = getByReserveStatus(isReserved);

        return sortProducts(sort, byReserveStatus);
    }

    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    private List<Product> sortProducts(ProductSortType sort, List<Product> products) {
        switch (sort) {
            case LOW_PRICE -> products.sort((product, t1) -> (int) (product.getPrice() - t1.getPrice()));
            case LATEST -> products.sort((product, t1) -> (int) (product.getId() - t1.getId()));
        }
        return products;
    }
}
