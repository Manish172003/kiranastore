package com.kiranastore.kirana_store.services.impl;


import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.entities.Supplier;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.ProductRepository;
import com.kiranastore.kirana_store.repositories.SupplierRepository;
import com.kiranastore.kirana_store.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final KiranaOwnerRepository ownerRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              KiranaOwnerRepository ownerRepository,
                              SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        KiranaOwner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + request.getOwnerId()));
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + request.getSupplierId()));

        Product product = mapToEntity(request, owner, supplier);
        return mapToResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByOwner(Long ownerId) {
        return productRepository.findByKiranaOwnerId(ownerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsBySupplier(Long supplierId) {
        return productRepository.findBySupplierId(supplierId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setName(request.getName());

        return mapToResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    // 🔹 Mapping functions
    private Product mapToEntity(ProductRequest request, KiranaOwner owner, Supplier supplier) {
        Product product = new Product();
        product.setName(request.getName());
        product.setOwner(owner);
        return product;
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setOwnerId(product.getOwner().getId());
        response.setOwnerName(product.getOwner().getName());
        return response;
    }
}
