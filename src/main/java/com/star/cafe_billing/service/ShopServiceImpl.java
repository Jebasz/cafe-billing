package com.star.cafe_billing.service;


import com.star.cafe_billing.entity.Shop;
import com.star.cafe_billing.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public Shop createShop(Shop shop) {

        return shopRepository.save(shop);

    }

    @Override
    public Shop updateShop(Long id, Shop shop) {

        Shop existing = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        existing.setShopName(shop.getShopName());
        existing.setAddress(shop.getAddress());
        existing.setPhone(shop.getPhone());

        return shopRepository.save(existing);
    }

    @Override
    public List<Shop> getAllShops() {

        return shopRepository.findAll();

    }

    @Override
    public void updateFilterType(Long shopId, String filterType) {

        Shop shop = shopRepository
                .findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        shop.setFilterType(filterType);

        shopRepository.save(shop);
    }

    @Override
    public Shop getShop(Long id) {

        return shopRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shop not found"));

    }
}