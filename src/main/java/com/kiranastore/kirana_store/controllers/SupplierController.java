package com.kiranastore.kirana_store.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kiranastore.kirana_store.dtos.SupplierRequest;
import com.kiranastore.kirana_store.dtos.SupplierResponse;
import com.kiranastore.kirana_store.services.SupplierService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> addSupplier(@RequestBody SupplierRequest supplier,HttpServletRequest request) {
        return ResponseEntity.ok(supplierService.addSupplier(supplier,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<SupplierResponse>> getSuppliersByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(supplierService.getSuppliersByOwner(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierRequest request) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

