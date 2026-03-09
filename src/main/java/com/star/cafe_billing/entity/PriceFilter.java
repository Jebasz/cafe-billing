package com.star.cafe_billing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "price_filters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double minPrice;

    private Double maxPrice;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
