package com.geeth.karma_test_backend.domain.service.impl;

import com.geeth.karma_test_backend.application.exception.ResourceNotFoundException;
import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.SaleDto;
import com.geeth.karma_test_backend.domain.dto.convertor.SaleMapper;
import com.geeth.karma_test_backend.domain.service.ISaleService;
import com.geeth.karma_test_backend.external.entity.Sale;
import com.geeth.karma_test_backend.external.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SaleServiceImpl implements ISaleService {
    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<SaleDto> getAllSales() {
        return saleMapper.convertToDtoList(saleRepository.findAll());
    }

    @Override
    public SaleDto getSaleById(Long id) {
        Sale sale = getSale(id);
        return saleMapper.convertToDto(sale);
    }

    @Override
    public SaleDto create(SaleCreateRequest saleCreateRequest) {

        Sale sale = saleMapper.convertToEntity(saleCreateRequest);
        saleRepository.save(sale);
        sale.setReference(String.format("SL-%05d", sale.getId()));
        saleRepository.save(sale);
        return saleMapper.convertToDto(sale);
    }

    @Override
    public SaleDto update(SaleUpdateRequest saleUpdateRequest) {
        Sale sale = getSale(saleUpdateRequest.getId());
        Sale savedSale = saleRepository.save(saleMapper.convertUpdateRequestToEntity(saleUpdateRequest, sale));
        return saleMapper.convertToDto(savedSale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = getSale(id);
        sale.setActive(0);
        sale.setDeletedAt(new Date());
        saleRepository.save(sale);
    }

    private Sale getSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + id));
        return sale;
    }
}
