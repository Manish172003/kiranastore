package com.kiranastore.kirana_store.services;
import java.util.List;
import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByOwner(Long ownerId);
    List<ProductResponse> getProductsBySupplier(Long supplierId);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
