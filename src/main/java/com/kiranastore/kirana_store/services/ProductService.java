package com.kiranastore.kirana_store.services;

import java.util.List;

import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;

public interface ProductService{
    List<ProductResponse> getAllProductsByOwner(Long ownerId);
    List<ProductResponse> getAllProductsBySupplier(Long supplierId);
    ProductResponse getProduct(Long id);
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}