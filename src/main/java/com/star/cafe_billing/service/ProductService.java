package com.star.cafe_billing.service;

import com.star.cafe_billing.dto.ProductRequest;
import com.star.cafe_billing.dto.ProductResponse;
import com.star.cafe_billing.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductRequest product);

    Product updateProduct(Long id, Product product);

    List<Product> getProductsByShop(Long shopId);

    List<Product> getProductsByCategory(Long shopId, Long categoryId);

    void disableProduct(Long id);

    // NEW METHOD (for re-enabling disabled product)
    void enableProduct(Long id);

    List<Product> getProducts(
            Long shopId,
            Long categoryId,
            Double minPrice,
            Double maxPrice
    );

    List<Product> getAllProductsByShop(Long shopId);

    List<ProductResponse> getFavouriteProducts(Long shopId);

    void toggleFavourite(Long productId);

    List<Product> searchProducts(Long shopId, String keyword);

    List<Product> getProductsBySubProduct(Long shopId, Long subProductId);

    Product createProductWithImage(
            String name,
            Double price,
            Long categoryId,
            Long shopId,
            Long subProductId,
            MultipartFile image
    );
}