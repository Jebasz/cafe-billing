package com.star.cafe_billing.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponse {

    private Double totalSales;

    private Long totalBills;

    private Double cashSales;

    private Double upiSales;

}