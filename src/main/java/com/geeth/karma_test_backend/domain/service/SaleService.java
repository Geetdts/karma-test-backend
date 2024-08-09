package com.geeth.karma_test_backend.domain.service;


import com.geeth.karma_test_backend.persistance.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    List<Sale> getAllSales();

    Optional<Sale> getSaleById(Long id);

    Sale saveSale(Sale sale);

    void deleteSale(Long id);
}

