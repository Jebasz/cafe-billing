package com.star.cafe_billing.service;

import com.star.cafe_billing.dto.ReportResponse;
import java.time.LocalDate;

public interface ReportService {

    ReportResponse getDailyReport(Long shopId, LocalDate date);

    ReportResponse getMonthlyReport(Long shopId, int year, int month);

    ReportResponse getYearlyReport(Long shopId, int year);

    ReportResponse getCustomReport(
            Long shopId,
            LocalDate fromDate,
            LocalDate toDate
    );

}