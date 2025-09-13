package com.kiranastore.kirana_store.services.impl;

import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.SupplierRequest;
import com.kiranastore.kirana_store.dtos.SupplierResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Supplier;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.SupplierRepository;
import com.kiranastore.kirana_store.services.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final KiranaOwnerRepository ownerRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository, KiranaOwnerRepository ownerRepository) {
        this.supplierRepository = supplierRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public SupplierResponse addSupplier(SupplierRequest request) {
        KiranaOwner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + request.getOwnerId()));
        Supplier supplier = mapToEntity(request, owner);
        return mapToResponse(supplierRepository.save(supplier));
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        return mapToResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getSuppliersByOwner(Long ownerId) {
        return supplierRepository.findByOwner_Id(ownerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));

        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        return mapToResponse(supplierRepository.save(supplier));
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        supplierRepository.delete(supplier);
    }

    // 🔹 Mapping functions
    private Supplier mapToEntity(SupplierRequest request, KiranaOwner owner) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());
        supplier.setOwner(owner);
        return supplier;
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
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
}
