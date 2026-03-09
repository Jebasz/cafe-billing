package com.star.cafe_billing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private Boolean favourite;
}