package com.star.cafe_billing.controller;

import com.star.cafe_billing.dto.ReportResponse;
import com.star.cafe_billing.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/daily")
    public ReportResponse dailyReport(
            @RequestParam Long shopId,
            @RequestParam String date){

        return reportService.getDailyReport(
                shopId,
                LocalDate.parse(date)
        );
    }

    @GetMapping("/monthly")
    public ReportResponse monthlyReport(
            @RequestParam Long shopId,
            @RequestParam int year,
            @RequestParam int month){

        return reportService
                .getMonthlyReport(shopId,year,month);
    }

    @GetMapping("/yearly")
    public ReportResponse yearlyReport(
            @RequestParam Long shopId,
            @RequestParam int year){

        return reportService
                .getYearlyReport(shopId,year);
    }

    @GetMapping("/custom")
    public ReportResponse customReport(
            @RequestParam Long shopId,
            @RequestParam String fromDate,
            @RequestParam String toDate
    ){
        return reportService.getCustomReport(
                shopId,
                LocalDate.parse(fromDate),
                LocalDate.parse(toDate)
        );
    }

}