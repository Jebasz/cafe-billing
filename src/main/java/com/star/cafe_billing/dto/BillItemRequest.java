package com.star.cafe_billing.dto;

import lombok.Data;

@Data
public class BillItemRequest {

    private Long productId;

    private Integer quantity;

}