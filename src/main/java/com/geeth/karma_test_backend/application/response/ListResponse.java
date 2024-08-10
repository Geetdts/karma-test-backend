package com.geeth.karma_test_backend.application.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> extends BaseResponseWrapper {
    private List<T> data;

    public ListResponse(List<T> data, Integer status, String message) {
        super(status, message);
        this.data = data;
    }

}
