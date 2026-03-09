package com.star.cafe_billing.service;


import com.star.cafe_billing.dto.BillRequest;
import com.star.cafe_billing.entity.Bill;

public interface BillService {

    Bill createBill(BillRequest request);

    Bill getBillById(Long id);

    void cancelBill(Long id);

}
