package com.star.cafe_billing.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String billNumber;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    // NEW FIELDS FOR SPLIT PAYMENT
    private Double cashAmount;

    private Double upiAmount;

    @Enumerated(EnumType.STRING)
    private BillStatus status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItem> items;

    @PrePersist
    public void setDefaults() {

        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = BillStatus.COMPLETED;
        }
    }
}