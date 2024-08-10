package com.geeth.karma_test_backend.application.response;


import lombok.Data;

import java.util.List;

@Data
public class PagingListResponse<T> extends BaseResponseWrapper {
    private List<T> data;
    private Pagination pagination;

    public PagingListResponse(List<T> data, Pagination pagination, Integer status, String message) {
        super(status, message);
        this.data = data;
        this.pagination = pagination;
    }

    @Data
    public static class Pagination {
        private Integer pageNumber;
        private Integer pageSize;
        private Integer totalPages;
        private Long totalRecords;

        public Pagination(Integer pageNumber, Integer pageSize, Integer totalPages, Long totalRecords) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.totalPages = totalPages;
            this.totalRecords = totalRecords;
        }
    }

}
