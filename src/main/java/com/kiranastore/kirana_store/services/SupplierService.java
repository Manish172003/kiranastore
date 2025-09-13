package com.kiranastore.kirana_store.services;

import java.util.List;

import com.kiranastore.kirana_store.dtos.SupplierRequest;
import com.kiranastore.kirana_store.dtos.SupplierResponse;

public interface SupplierService{
    List<SupplierResponse> getAllSuppliersByOwner(Long ownerId);
    SupplierResponse getSupplier(Long supplierId);
    SupplierResponse addSupplier(SupplierRequest request);
    SupplierResponse updateSupplier(Long supplierId, SupplierRequest request);
    void deleteSupplier(Long supplierId);
}