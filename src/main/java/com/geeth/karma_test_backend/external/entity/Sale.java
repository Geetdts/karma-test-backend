package com.geeth.karma_test_backend.external.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Where(clause = "deleted_at is null")
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "reference")
    private String reference;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status")
    private Status status;// e.g., Completed, Pending

    @Column(name = "grand_total", nullable = false)
    private Double grandTotal;

    @Column(name = "paid", nullable = false)
    private Double paid;

    @Column(name = "due")
    private Double due;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus; // e.g., Paid, Due

    @Column(name = "biller")
    private String biller;

    @Column(name = "active")
    private Integer active;

    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @NotNull
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @NotNull
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

}
