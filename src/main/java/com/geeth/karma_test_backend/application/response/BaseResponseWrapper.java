package com.geeth.karma_test_backend.application.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class BaseResponseWrapper {
    private Integer status;
    private String message;

    private Date timestamp = new Date();

    public BaseResponseWrapper(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}