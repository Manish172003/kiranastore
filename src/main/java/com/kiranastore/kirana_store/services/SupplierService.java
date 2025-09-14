package com.kiranastore.kirana_store.services;
import java.util.List;

import com.kiranastore.kirana_store.dtos.SupplierRequest;
import com.kiranastore.kirana_store.dtos.SupplierResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface SupplierService {
    SupplierResponse addSupplier(SupplierRequest supplier,HttpServletRequest request);
    SupplierResponse getSupplierById(Long id);
    List<SupplierResponse> getSuppliersByOwner(Long ownerId);
    SupplierResponse updateSupplier(Long id, SupplierRequest request);
    void deleteSupplier(Long id);
}
