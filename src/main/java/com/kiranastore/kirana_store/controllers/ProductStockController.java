package com.kiranastore.kirana_store.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiranastore.kirana_store.dtos.ProductStockRequest;
import com.kiranastore.kirana_store.dtos.ProductStockResponse;
import com.kiranastore.kirana_store.services.ProductStockService;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class ProductStockController {

    private final ProductStockService stockService;

    public ProductStockController(ProductStockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<ProductStockResponse> addStock(@RequestBody ProductStockRequest request) {
        return ResponseEntity.ok(stockService.addStock(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductStockResponse> getStock(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductStockResponse>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductStockResponse> updateStock(@PathVariable Long id,
                                                            @RequestBody ProductStockRequest request) {
        return ResponseEntity.ok(stockService.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}

