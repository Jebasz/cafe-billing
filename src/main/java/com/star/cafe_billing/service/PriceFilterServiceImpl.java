package com.star.cafe_billing.service;

import com.star.cafe_billing.dto.ProductResponse;
import com.star.cafe_billing.entity.PriceFilter;
import com.star.cafe_billing.entity.Product;
import com.star.cafe_billing.repository.PriceFilterRepository;
import com.star.cafe_billing.repository.ShopRepository;
import com.star.cafe_billing.repository.SubProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceFilterServiceImpl implements PriceFilterService {

    private final PriceFilterRepository priceFilterRepository;
    private final ShopRepository shopRepository;



    @Override
    public PriceFilter createPriceFilter(PriceFilter priceFilter) {

        shopRepository.findById(priceFilter.getShop().getId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        return priceFilterRepository.save(priceFilter);
    }

    @Override
    public List<PriceFilter> getPriceFiltersByShop(Long shopId) {

        return priceFilterRepository.findByShopId(shopId);

    }

    @Override
    public PriceFilter updatePriceFilter(Long id, PriceFilter priceFilter) {

        PriceFilter existing = priceFilterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price filter not found"));

        existing.setMinPrice(priceFilter.getMinPrice());
        existing.setMaxPrice(priceFilter.getMaxPrice());

        return priceFilterRepository.save(existing);
    }

    @Override
    public void deletePriceFilter(Long id) {

        priceFilterRepository.deleteById(id);

    }




}