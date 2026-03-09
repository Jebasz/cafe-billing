package com.star.cafe_billing.controller;

import com.star.cafe_billing.entity.SubProduct;
import com.star.cafe_billing.service.SubProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-products")
@RequiredArgsConstructor
public class SubProductController {

    private final SubProductService subProductService;

    @GetMapping
    public List<SubProduct> getSubProducts(

            @RequestParam Long shopId,
            @RequestParam Long categoryId

    ) {

        return subProductService
                .getSubProducts(shopId, categoryId);

    }
}