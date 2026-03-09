package com.star.cafe_billing.controller;


import com.star.cafe_billing.entity.Shop;
import com.star.cafe_billing.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping
    public Shop createShop(@RequestBody Shop shop){

        return shopService.createShop(shop);

    }

    @PutMapping("/{id}")
    public Shop updateShop(
            @PathVariable Long id,
            @RequestBody Shop shop){

        return shopService.updateShop(id,shop);

    }

    @GetMapping
    public List<Shop> getShops(){

        return shopService.getAllShops();

    }

    @PatchMapping("/{id}/filter-type")
    public void updateFilterType(
            @PathVariable Long id,
            @RequestParam String filterType
    ){
        shopService.updateFilterType(id, filterType);
    }

    @GetMapping("/{id}")
    public Shop getShop(@PathVariable Long id){
        return shopService.getShop(id);
    }

}
