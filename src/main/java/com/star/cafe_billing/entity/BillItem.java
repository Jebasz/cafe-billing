package com.star.cafe_billing.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bill_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double price;

    private Integer quantity;

    private Double total;
}