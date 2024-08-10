package com.geeth.karma_test_backend.application.controller;

import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.application.response.ListResponse;
import com.geeth.karma_test_backend.application.response.ObjectResponse;
import com.geeth.karma_test_backend.application.util.SystemMessage;
import com.geeth.karma_test_backend.domain.dto.SaleDto;
import com.geeth.karma_test_backend.domain.service.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for managing Sale-related operations.
 */
@CrossOrigin
@Tag(name = "Sale", description = "Sale APIs.")
@RestController
@RequestMapping("/api/v1/sale")
public class SalesController {

    @Autowired
    private ISaleService iSaleService;

    /**
     * Get all active sales (not deleted).
     *
     * @return - A ResponseEntity containing a list of active sales.
     */
    @Operation(summary = "Get all active sales (not deleted)")
    @GetMapping
    public ResponseEntity<ListResponse> getAllSales() {
        List<SaleDto> saleDtoList = iSaleService.getAllSales();
        return new ResponseEntity<>(new ListResponse<>(saleDtoList, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    /**
     * Get a specific sale by ID.
     *
     * @param id - The ID of the sale to retrieve.
     * @return - A ResponseEntity containing the details of the sale.
     */
    @Operation(summary = "Get a specific sale by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getSaleById(@PathVariable Long id) {

        SaleDto saleDto = iSaleService.getSaleById(id);
        return new ResponseEntity<>(new ObjectResponse<>(saleDto, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    /**
     * Create a new Sale.
     *
     * @param saleCreateRequest - Contains the details of the sale to be created.
     * @return - A ResponseEntity containing the created sale's details with a created status.
     */
    @Operation(summary = "Create new Sale")
    @PostMapping
    public ResponseEntity<ObjectResponse> createSale(@RequestBody SaleCreateRequest saleCreateRequest) {

        SaleDto saleDto = iSaleService.create(saleCreateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(saleDto, HttpStatus.CREATED.value(), SystemMessage.CREATE),
                HttpStatus.CREATED);

    }

    /**
     * Update an existing Sale.
     *
     * @param saleUpdateRequest - Contains the updated details of the sale.
     * @return - A ResponseEntity containing the updated sale's details.
     */
    @Operation(summary = "Update existing Sale")
    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> updateSale(@PathVariable Long id,@RequestBody SaleUpdateRequest saleUpdateRequest) {

        SaleDto updatedSaleDto = iSaleService.update(id,   saleUpdateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(updatedSaleDto, HttpStatus.OK.value(), SystemMessage.UPDATE),
                HttpStatus.OK);
    }

    /**
     * Delete a sale by ID.
     *
     * @param id - The ID of the sale to be deleted.
     * @return - A ResponseEntity with a success status indicating that the sale has been deleted.
     */
    @Operation(summary = "Delete a sale")
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> deleteSale(@PathVariable Long id) {

        iSaleService.deleteSale(id);
        return new ResponseEntity<>(new ObjectResponse<>(null, HttpStatus.OK.value(), SystemMessage.DELETE),
                HttpStatus.OK);
    }
}