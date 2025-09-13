package com.kiranastore.kirana_store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;
import com.kiranastore.kirana_store.services.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController{

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ProductResponse>> getProductsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(productService.getAllProductsByOwner(ownerId));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductResponse>> getProductsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(productService.getAllProductsBySupplier(supplierId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}