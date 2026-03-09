package com.star.cafe_billing.repository;

import com.star.cafe_billing.entity.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubProductRepository extends JpaRepository<SubProduct, Long> {

    List<SubProduct> findByShopIdAndCategoryIdAndActiveTrue(
            Long shopId,
            Long categoryId
    );

}