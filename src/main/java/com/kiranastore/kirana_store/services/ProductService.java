package com.kiranastore.kirana_store.services;

import java.util.List;
import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    ProductResponse createProduct(ProductRequest product,HttpServletRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByOwner(Long ownerId);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
    public List<ProductResponse> getAllProducts();
}
