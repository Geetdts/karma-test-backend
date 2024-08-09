package com.geeth.karma_test_backend.application;

import com.geeth.karma_test_backend.domain.service.SaleService;
import com.geeth.karma_test_backend.persistance.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SaleService salesService;

    // Get all sales
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> salesList = salesService.getAllSales();
        return ResponseEntity.ok(salesList);
    }

    // Get a specific sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale sale = salesService.getSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new sale
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = salesService.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    // Update an existing sale
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        Sale updatedSale = salesService.updateSale(id, sale);
        if (updatedSale != null) {
            return ResponseEntity.ok(updatedSale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a sale
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        boolean isDeleted = salesService.deleteSale(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}