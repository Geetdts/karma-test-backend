package com.geeth.karma_test_backend.domain.service;


import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.SaleDto;

import java.util.List;

public interface ISaleService {

    List<SaleDto> getAllSales();

    SaleDto getSaleById(Long id);

    SaleDto create(SaleCreateRequest saleCreateRequest);

    SaleDto update(SaleUpdateRequest saleUpdateRequest);

    void deleteSale(Long id);


}

