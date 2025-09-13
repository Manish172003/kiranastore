package com.kiranastore.kirana_store.services.impl;

import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.CustomerResponse;
import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.dtos.ProductResponse;
import com.kiranastore.kirana_store.dtos.ProductStockResponse;
import com.kiranastore.kirana_store.dtos.SupplierResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.services.KiranaOwnerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KiranaOwnerServiceImpl implements KiranaOwnerService {

    private final KiranaOwnerRepository ownerRepository;

    public KiranaOwnerServiceImpl(KiranaOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public KiranaOwnerResponse registerOwner(KiranaOwnerRequest request) {
        
        KiranaOwner owner = mapToEntity(request);
        return mapToResponse(ownerRepository.save(owner));
    }

    @Override
    public KiranaOwnerResponse getOwnerById(Long id) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        return mapToResponse(owner);
    }

    @Override
    public List<KiranaOwnerResponse> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KiranaOwnerResponse updateOwner(Long id, KiranaOwnerRequest request) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));

        owner.setName(request.getName());
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return mapToResponse(ownerRepository.save(owner));
    }

    @Override
    public void deleteOwner(Long id) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        ownerRepository.delete(owner);
    }

    // 🔹 Mapping functions
    private KiranaOwner mapToEntity(KiranaOwnerRequest request) {
        KiranaOwner owner = new KiranaOwner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        owner.setPassword(request.getPassword()); 
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return owner;
    }

	    private KiranaOwnerResponse mapToResponse(KiranaOwner owner) {
	        KiranaOwnerResponse response = new KiranaOwnerResponse();
	        response.setId(owner.getId());
	        response.setName(owner.getName());
	        response.setEmail(owner.getEmail());
	        response.setStoreName(owner.getStoreName());
	        response.setPhone(owner.getPhone());
	        response.setAddress(owner.getAddress());
	        
	        if (owner.getCustomers() != null) {
	            List<CustomerResponse> customerResponses = owner.getCustomers()
	                    .stream()
	                    .map(customer -> {
	                        CustomerResponse cr = new CustomerResponse();
	                        cr.setId(customer.getId());
	                        cr.setName(customer.getName());
	                        cr.setPhone(customer.getPhone());
	                        cr.setEmail(customer.getEmail());
	                        cr.setAddress(customer.getAddress());
	                        cr.setOwnerId(customer.getOwner().getId());
	                        cr.setOwnerName(customer.getOwner().getName());
	                        return cr;
	                    })
	                    .collect(Collectors.toList());
	            response.setCustomers(customerResponses);
	        }
	        
	        if (owner.getSuppliers() != null) {
	            List<SupplierResponse> supplierResponses = owner.getSuppliers()
	                    .stream()
	                    .map(supplier -> {
	                        SupplierResponse sr = new SupplierResponse();
	                        sr.setId(supplier.getId());
	                        sr.setName(supplier.getName());
	                        sr.setPhone(supplier.getPhone());
	                        sr.setEmail(supplier.getEmail());
	                        sr.setAddress(supplier.getAddress());
	                        sr.setOwnerId(supplier.getOwner().getId());
	                        sr.setOwnerName(supplier.getOwner().getName());
	                        return sr;
	                    })
	                    .collect(Collectors.toList());

	            response.setSuppliers(supplierResponses);
	        }
	        
	        if (owner.getProducts() != null) {
	            List<ProductResponse> productResponses = owner.getProducts().stream()
	                    .map(product -> {
	                        ProductResponse pr = new ProductResponse();
	                        pr.setId(product.getId());
	                        pr.setName(product.getName());
	                        pr.setOwnerId(owner.getId());
	                        pr.setOwnerName(owner.getName());

	                        // Map product stocks if available
	                        if (product.getProductStocks() != null) {
	                            List<ProductStockResponse> stockResponses = product.getProductStocks()
	                                    .stream()
	                                    .map(stock -> new ProductStockResponse(
	                                            stock.getId(),
	                                            stock.getPrice(),
	                                            stock.getQuantity(),
	                                            stock.getProduct().getId(),
	                                            stock.getSupplier().getId()
	                                    ))
	                                    .collect(Collectors.toList());
	                            pr.setProductStocks(stockResponses);
	                        }

	                        return pr;
	                    })
	                    .collect(Collectors.toList());

	            response.setProducts(productResponses); 
	        }

	        
	        return response;
	    }
}

