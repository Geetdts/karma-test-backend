package com.geeth.karma_test_backend.application.request;

import lombok.Data;

import java.util.Date;

@Data
public class SaleCreateRequest {

    private String customerName;

    private Date paymentDate;

    private Double grandTotal;

    private Double paid;

    private String biller;

}
