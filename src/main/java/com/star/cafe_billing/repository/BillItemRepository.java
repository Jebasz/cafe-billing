package com.star.cafe_billing.repository;


import com.star.cafe_billing.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {

    List<BillItem> findByBillId(Long billId);

}
