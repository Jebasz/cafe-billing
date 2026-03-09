package com.star.cafe_billing.service;

import com.star.cafe_billing.dto.ReportResponse;
import com.star.cafe_billing.entity.Bill;
import com.star.cafe_billing.entity.BillStatus;
import com.star.cafe_billing.entity.PaymentType;
import com.star.cafe_billing.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final BillRepository billRepository;

    @Override
    public ReportResponse getDailyReport(Long shopId, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23,59,59);

        List<Bill> bills = billRepository
                .findByShopIdAndCreatedAtBetween(shopId,start,end);

        return calculateReport(bills);
    }

    @Override
    public ReportResponse getMonthlyReport(Long shopId, int year, int month) {

        LocalDateTime start =
                LocalDate.of(year,month,1).atStartOfDay();

        LocalDateTime end =
                LocalDate.of(year,month,1)
                        .plusMonths(1)
                        .minusDays(1)
                        .atTime(23,59,59);

        List<Bill> bills = billRepository
                .findByShopIdAndCreatedAtBetween(shopId,start,end);

        return calculateReport(bills);
    }

    @Override
    public ReportResponse getYearlyReport(Long shopId, int year) {

        LocalDateTime start =
                LocalDate.of(year,1,1).atStartOfDay();

        LocalDateTime end =
                LocalDate.of(year,12,31)
                        .atTime(23,59,59);

        List<Bill> bills = billRepository
                .findByShopIdAndCreatedAtBetween(shopId,start,end);

        return calculateReport(bills);
    }

    private ReportResponse calculateReport(List<Bill> bills){

        double totalSales = 0;
        double cashSales = 0;
        double upiSales = 0;

        for(Bill bill : bills){

            if(bill.getStatus() == BillStatus.CANCELLED){
                continue;
            }

            totalSales += bill.getTotalAmount();

            if(bill.getPaymentType() == PaymentType.CASH){

                cashSales += bill.getTotalAmount();

            }
            else if(bill.getPaymentType() == PaymentType.UPI){

                upiSales += bill.getTotalAmount();

            }
            else if(bill.getPaymentType() == PaymentType.SPLIT){

                if(bill.getCashAmount() != null){
                    cashSales += bill.getCashAmount();
                }

                if(bill.getUpiAmount() != null){
                    upiSales += bill.getUpiAmount();
                }
            }

        }

        return ReportResponse.builder()
                .totalSales(totalSales)
                .totalBills((long) bills.size())
                .cashSales(cashSales)
                .upiSales(upiSales)
                .build();

    }

    @Override
    public ReportResponse getCustomReport(
            Long shopId,
            LocalDate fromDate,
            LocalDate toDate) {

        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date cannot be after To date");
        }

        LocalDateTime start = fromDate.atStartOfDay();
        LocalDateTime end = toDate.atTime(23, 59, 59);

        List<Bill> bills = billRepository
                .findByShopIdAndCreatedAtBetween(shopId, start, end);

        return calculateReport(bills);
    }

}