package com.kiranastore.kirana_store.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.entities.Supplier;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.ProductRepository;
import com.kiranastore.kirana_store.repositories.SupplierRepository;
import com.kiranastore.kirana_store.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KiranaOwnerRepository ownerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<ProductResponse> getAllProductsByOwner(Long ownerId) {
        KiranaOwner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + ownerId));
        return productRepository.findByOwner(owner)
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductsBySupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
               .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + supplierId));        
        return productRepository.findBySupplier(supplier)
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapResponse(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        KiranaOwner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + request.getOwnerId()));
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
               .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + request.getSupplierId()));
        Product product = mapEntity(request, owner, supplier);
        return mapResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        //product.setPrice(request.getPrice());
        //product.setQuantity(request.getQuantity());

        return mapResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    private Product mapEntity(ProductRequest request, KiranaOwner owner, Supplier supplier){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        //product.setPrice(request.getPrice());
        //product.setQuantity(request.getQuantity());
        product.setOwner(owner);
        product.setSupplier(supplier);
        return product;
    }

    private ProductResponse mapResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        //response.setPrice(product.getPrice());
        //response.setQuantity(product.getQuantity());
        response.setOwnerId(product.getOwner().getId());
        response.setOwnerName(product.getOwner().getName());
        response.setSupplierId(product.getSupplier().getId());
        response.setSupplierName(product.getSupplier().getName());
        return response;
    }
    
}