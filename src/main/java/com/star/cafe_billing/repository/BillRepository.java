package com.star.cafe_billing.repository;

import com.star.cafe_billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByBillNumber(String billNumber);

    List<Bill> findByShopId(Long shopId);

    List<Bill> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Bill> findByShopIdAndCreatedAtBetween(
            Long shopId,
            LocalDateTime start,
            LocalDateTime end
    );

    long countByShopIdAndCreatedAtBetween(Long shopId, LocalDateTime start, LocalDateTime end);
}