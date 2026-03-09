package com.star.cafe_billing.service;

import com.star.cafe_billing.dto.BillItemRequest;
import com.star.cafe_billing.dto.BillRequest;
import com.star.cafe_billing.entity.*;
import com.star.cafe_billing.repository.BillRepository;
import com.star.cafe_billing.repository.ProductRepository;
import com.star.cafe_billing.repository.ShopRepository;
import com.star.cafe_billing.util.BillNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final BillNumberGenerator billNumberGenerator;

    @Override
    public Bill createBill(BillRequest request){

        Shop shop = shopRepository
                .findById(request.getShopId())
                .orElseThrow(() ->
                        new RuntimeException("Shop not found"));

        Bill bill = new Bill();

        bill.setShop(shop);

        bill.setPaymentType(
                PaymentType.valueOf(
                        request.getPaymentType()
                )
        );

        // SPLIT PAYMENT SUPPORT
        if ("SPLIT".equalsIgnoreCase(request.getPaymentType())) {

            bill.setCashAmount(request.getCashAmount());

            bill.setUpiAmount(request.getUpiAmount());

        }

        bill.setStatus(BillStatus.COMPLETED);

        bill.setBillNumber(
                billNumberGenerator.generateBillNumber(shop.getId())
        );

        List<BillItem> billItems = new ArrayList<>();

        double totalAmount = 0;

        for(BillItemRequest itemRequest :
                request.getItems()){

            Product product = productRepository
                    .findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            BillItem billItem = new BillItem();

            billItem.setBill(bill);
            billItem.setProduct(product);
            billItem.setPrice(product.getPrice());
            billItem.setQuantity(itemRequest.getQuantity());

            double total =
                    product.getPrice()
                            * itemRequest.getQuantity();

            billItem.setTotal(total);

            totalAmount += total;

            billItems.add(billItem);
        }

        bill.setItems(billItems);
        bill.setTotalAmount(totalAmount);

        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {

        return billRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));

    }

    @Override
    public void cancelBill(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));

        bill.setStatus(BillStatus.CANCELLED);

        billRepository.save(bill);
    }
}