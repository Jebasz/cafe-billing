package com.star.cafe_billing.dto;

import lombok.Data;
import java.util.List;

@Data
public class BillRequest {

    private Long shopId;

    private String paymentType;

    private Double cashAmount;

    private Double upiAmount;

    private List<BillItemRequest> items;

}