package com.star.cafe_billing.controller;
import com.star.cafe_billing.entity.PriceFilter;
import com.star.cafe_billing.service.PriceFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-filters")
@RequiredArgsConstructor
public class PriceFilterController {

    private final PriceFilterService priceFilterService;

    @PostMapping
    public PriceFilter createFilter(
            @RequestBody PriceFilter filter){

        return priceFilterService.createPriceFilter(filter);

    }

    @GetMapping("/shop/{shopId}")
    public List<PriceFilter> getFilters(
            @PathVariable Long shopId){

        return priceFilterService
                .getPriceFiltersByShop(shopId);

    }

    @PutMapping("/{id}")
    public PriceFilter updateFilter(
            @PathVariable Long id,
            @RequestBody PriceFilter filter){

        return priceFilterService
                .updatePriceFilter(id,filter);

    }

    @DeleteMapping("/{id}")
    public void deleteFilter(@PathVariable Long id){

        priceFilterService.deletePriceFilter(id);

    }

}