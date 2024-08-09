package com.geeth.karma_test_backend.persistance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "status")
    private String status; // e.g., Completed, Pending

    @Column(name = "grand_total", nullable = false)
    private BigDecimal grandTotal;

    @Column(name = "paid", nullable = false)
    private BigDecimal paid;

    @Column(name = "due")
    private BigDecimal due;

    @Column(name = "payment_status")
    private String paymentStatus; // e.g., Paid, Due

    @Column(name = "biller")
    private String biller;

}
