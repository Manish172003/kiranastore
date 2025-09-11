package com.kiranastore.kirana_store.services;

import java.util.List;
import com.kiranastore.kirana_store.dtos.ProductStockRequest;
import com.kiranastore.kirana_store.dtos.ProductStockResponse;

public interface ProductStockService {
    ProductStockResponse addStock(ProductStockRequest request);
    ProductStockResponse getStockById(Long id);
    List<ProductStockResponse> getAllStocks();
    ProductStockResponse updateStock(Long id, ProductStockRequest request);
    void deleteStock(Long id);
}
