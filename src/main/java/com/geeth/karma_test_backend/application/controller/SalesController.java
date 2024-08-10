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

@CrossOrigin
@Tag(name = "Sale")
@RestController
@RequestMapping("/api/v1/sale")
public class SalesController {

    @Autowired
    private ISaleService iSaleService;

    @Operation(summary = "Get all active sales (not deleted)")
    @GetMapping
    public ResponseEntity<ListResponse> getAllSales() {
        List<SaleDto> saleDtoList = iSaleService.getAllSales();
        return new ResponseEntity<>(new ListResponse<>(saleDtoList, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    @Operation(summary = "Get a specific sale by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getSaleById(@PathVariable Long id) {

        SaleDto saleDto = iSaleService.getSaleById(id);
        return new ResponseEntity<>(new ObjectResponse<>(saleDto, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    @Operation(summary = "Create new Sale")
    @PostMapping
    public ResponseEntity<ObjectResponse> createSale(@RequestBody SaleCreateRequest saleCreateRequest) {

        SaleDto saleDto = iSaleService.create(saleCreateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(saleDto, HttpStatus.CREATED.value(), SystemMessage.CREATE),
                HttpStatus.CREATED);

    }

    @Operation(summary = "Update existing Sale")
    @PutMapping()
    public ResponseEntity<ObjectResponse> updateSale(@RequestBody SaleUpdateRequest saleUpdateRequest) {

        SaleDto updatedSaleDto = iSaleService.update(saleUpdateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(updatedSaleDto, HttpStatus.OK.value(), SystemMessage.UPDATE),
                HttpStatus.OK);
    }


    @Operation(summary = "Delete a sale")
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> deleteSale(@PathVariable Long id) {

        iSaleService.deleteSale(id);
        return new ResponseEntity<>(new ObjectResponse<>(null, HttpStatus.OK.value(), SystemMessage.DELETE),
                HttpStatus.OK);
    }
}