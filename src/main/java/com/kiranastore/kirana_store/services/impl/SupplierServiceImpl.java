package com.kiranastore.kirana_store.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.SupplierRequest;
import com.kiranastore.kirana_store.dtos.SupplierResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Supplier;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.SupplierRepository;
import com.kiranastore.kirana_store.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private KiranaOwnerRepository kiranaOwnerRepository;

    @Override
    public List<SupplierResponse> getAllSuppliersByOwner(Long ownerId) {
        KiranaOwner owner = kiranaOwnerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Kirana Owner not found with id: " + ownerId));
        return supplierRepository.findAllByOwner(owner)
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponse getSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + supplierId));
        return mapResponse(supplier);
    }

    @Override
    public SupplierResponse addSupplier(SupplierRequest request) {
        KiranaOwner owner = kiranaOwnerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + request.getOwnerId()));
        Supplier supplier = mapEntity(request, owner);
        return mapResponse(supplierRepository.save(supplier));
    }

    @Override
    public SupplierResponse updateSupplier(Long supplierId, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + supplierId));

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        return mapResponse(supplierRepository.save(supplier));
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + supplierId));
        supplierRepository.delete(supplier);
    }

    private SupplierResponse mapResponse(Supplier supplier){
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setEmail(supplier.getEmail());
        response.setPhone(supplier.getPhone());
        response.setAddress(supplier.getAddress());
        response.setOwnerId(supplier.getOwner().getId());
        response.setOwnerName(supplier.getOwner().getName());
        return response;
    }

    private Supplier mapEntity(SupplierRequest request, KiranaOwner owner){
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());
        supplier.setOwner(owner);
        return supplier;
    }
    
}