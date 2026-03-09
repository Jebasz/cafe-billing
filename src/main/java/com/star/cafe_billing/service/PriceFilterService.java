package com.star.cafe_billing.service;


import com.star.cafe_billing.entity.PriceFilter;

import java.util.List;

public interface PriceFilterService {

    PriceFilter createPriceFilter(PriceFilter priceFilter);

    List<PriceFilter> getPriceFiltersByShop(Long shopId);

    PriceFilter updatePriceFilter(Long id, PriceFilter priceFilter);

    void deletePriceFilter(Long id);

}