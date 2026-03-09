package com.star.cafe_billing.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Double price;
    private Long categoryId;
    private Long shopId;
    private Long subProductId;
}