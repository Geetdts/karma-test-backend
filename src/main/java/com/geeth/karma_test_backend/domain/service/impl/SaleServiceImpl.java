package com.geeth.karma_test_backend.domain.service.impl;

import com.geeth.karma_test_backend.application.exception.ResourceNotFoundException;
import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.SaleDto;
import com.geeth.karma_test_backend.domain.dto.convertor.SaleMapper;
import com.geeth.karma_test_backend.domain.service.ISaleService;
import com.geeth.karma_test_backend.external.entity.Sale;
import com.geeth.karma_test_backend.external.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service implementation class responsible for managing Sale-related operations.
 */

@Service
public class SaleServiceImpl implements ISaleService {
    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleRepository saleRepository;

    /**
     * Get all active sales (not deleted).
     *
     * @return - A list of SaleDto representing all active sales.
     */
    @Override
    public List<SaleDto> getAllSales() {
        return saleMapper.convertToDtoList(saleRepository.findAll());
    }

    /**
     * Get a specific sale by ID.
     *
     * @param id - The ID of the sale to retrieve.
     * @return - A SaleDto representing the sale with the specified ID.
     */
    @Override
    public SaleDto getSaleById(Long id) {
        Sale sale = getSale(id);
        return saleMapper.convertToDto(sale);
    }

    /**
     * Create a new Sale.
     *
     * @param saleCreateRequest - Contains the details of the sale to be created.
     * @return - A SaleDto representing the created sale.
     */
    @Override
    public SaleDto create(SaleCreateRequest saleCreateRequest) {

        Sale sale = saleMapper.convertToEntity(saleCreateRequest);
        saleRepository.save(sale);
        sale.setReference(String.format("SL-%05d", sale.getId()));
        saleRepository.save(sale);
        return saleMapper.convertToDto(sale);
    }

    /**
     * Update an existing Sale.
     *
     * @param saleUpdateRequest - Contains the updated details of the sale.
     * @return - A SaleDto representing the updated sale.
     */
    @Override
    public SaleDto update(SaleUpdateRequest saleUpdateRequest) {
        Sale sale = getSale(saleUpdateRequest.getId());
        Sale savedSale = saleRepository.save(saleMapper.convertUpdateRequestToEntity(saleUpdateRequest, sale));
        return saleMapper.convertToDto(savedSale);
    }

    /**
     * Soft delete a Sale by marking it as inactive and setting the deleted timestamp.
     *
     * @param id - The ID of the sale to delete.
     */
    @Override
    public void deleteSale(Long id) {
        Sale sale = getSale(id);
        sale.setActive(0);
        sale.setDeletedAt(new Date());
        saleRepository.save(sale);
    }

    /**
     * Retrieve a sale by its ID.
     *
     * @param id - The ID of the sale to retrieve.
     * @return - The Sale entity.
     * @throws ResourceNotFoundException - If no sale is found with the given ID.
     */
    private Sale getSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with ID: " + id));
        return sale;
    }
}
