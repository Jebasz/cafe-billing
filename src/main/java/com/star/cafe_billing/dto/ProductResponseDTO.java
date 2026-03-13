package com.star.cafe_billing.dto;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private String imageUrl;

    private Long categoryId;
    private Long subProductId;

    private Boolean favourite;

}