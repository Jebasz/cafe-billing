package com.star.cafe_billing.repository;

import com.star.cafe_billing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByShopId(Long shopId);

    List<Product> findByCategoryIdAndShopId(Long categoryId, Long shopId);

    List<Product> findByShopIdAndCategoryIdAndPriceBetweenAndActiveTrue(

            Long shopId,

            Long categoryId,

            Double minPrice,

            Double maxPrice

    );

    List<Product> findByPriceBetweenAndShopId(Double minPrice, Double maxPrice, Long shopId);

    List<Product> findByActiveTrueAndShopId(Long shopId);

    List<Product> findByShopIdAndFavouriteTrueAndActiveTrue(Long shopId);

    // 🔍 NEW METHOD FOR PRODUCT SEARCH
    List<Product> findByShopIdAndNameContainingIgnoreCaseAndActiveTrue(Long shopId, String keyword);

    List<Product> findByShopIdAndSubProductIdAndActiveTrue(
            Long shopId,
            Long subProductId
    );

}