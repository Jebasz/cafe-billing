package com.star.cafe_billing.service;

import com.star.cafe_billing.entity.SubProduct;
import com.star.cafe_billing.repository.SubProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubProductServiceImpl implements SubProductService {

    private final SubProductRepository subProductRepository;

    @Override
    public List<SubProduct> getSubProducts(
            Long shopId,
            Long categoryId
    ) {

        return subProductRepository
                .findByShopIdAndCategoryIdAndActiveTrue(
                        shopId,
                        categoryId
                );

    }
}