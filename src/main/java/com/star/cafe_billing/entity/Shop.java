package com.star.cafe_billing.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    private String address;

    private String phone;

    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
    @Column(name = "filter_type")
    private String filterType;
}
