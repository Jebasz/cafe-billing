package com.star.cafe_billing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_product_id")
    private SubProduct subProduct;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private Shop shop;

    @Builder.Default
    private Boolean active = true;

    // ⭐ NEW FIELD
    @Builder.Default
    @Column(nullable = false)
    private Boolean favourite = false;
}