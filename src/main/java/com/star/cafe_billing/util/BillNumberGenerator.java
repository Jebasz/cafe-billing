package com.star.cafe_billing.util;

import com.star.cafe_billing.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class BillNumberGenerator {

    private final BillRepository billRepository;



    public String generateBillNumber(Long shopId) {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59, 59);

        long count = billRepository
                .countByShopIdAndCreatedAtBetween(shopId, start, end);

        long nextNumber = count + 1;

        String datePart = today
                .format(DateTimeFormatter.ofPattern("ddMMyy"));

        String runningNumber = String.format("%03d", nextNumber);

        return "SHOP" + shopId + "-" + datePart + "-" + runningNumber;
    }
}