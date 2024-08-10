package com.geeth.karma_test_backend.application.response;

import lombok.Data;

@Data
public class ObjectResponse<T> extends BaseResponseWrapper {
    private T data;

    public ObjectResponse(T data, Integer status, String message) {
        super(status, message);
        this.data = data;
    }

}
