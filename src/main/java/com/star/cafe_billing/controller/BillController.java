package com.star.cafe_billing.controller;


import com.star.cafe_billing.dto.BillRequest;
import com.star.cafe_billing.entity.Bill;
import com.star.cafe_billing.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public Bill createBill(
            @RequestBody BillRequest request){

        return billService.createBill(request);

    }

    @GetMapping("/{id}")
    public Bill getBill(@PathVariable Long id){

        return billService.getBillById(id);

    }

    @PutMapping("/{id}/cancel")
    public void cancelBill(@PathVariable Long id){

        billService.cancelBill(id);

    }

}