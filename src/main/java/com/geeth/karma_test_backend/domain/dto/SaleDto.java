package com.geeth.karma_test_backend.domain.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SaleDto {

    private Long id;

    private String customerName;

    private String reference;

    private Date paymentDate;

    private String status; // e.g., Completed, Pending

    private Double grandTotal;

    private Double paid;

    private Double due;

    private String paymentStatus; // e.g., Paid, Due

    private String biller;

    private Integer active;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    private String deletedBy;

    private Date deletedAt;


}
