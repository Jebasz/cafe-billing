package com.star.cafe_billing.service;

import com.star.cafe_billing.entity.SubProduct;
import java.util.List;

public interface SubProductService {

    List<SubProduct> getSubProducts(
            Long shopId,
            Long categoryId
    );

}