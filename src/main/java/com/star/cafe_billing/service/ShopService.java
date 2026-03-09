package com.star.cafe_billing.service;

import com.star.cafe_billing.entity.Shop;

import java.util.List;

public interface ShopService {

    Shop createShop(Shop shop);

    Shop updateShop(Long id, Shop shop);

    List<Shop> getAllShops();

    void updateFilterType(Long shopId, String filterType);

    Shop getShop(Long id);

}