package com.kiranastore.kirana_store.services.impl;

import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.ProductStockRequest;
import com.kiranastore.kirana_store.dtos.ProductStockResponse;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.entities.ProductStock;
import com.kiranastore.kirana_store.entities.Supplier;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.ProductRepository;
import com.kiranastore.kirana_store.repositories.ProductStockRepository;
import com.kiranastore.kirana_store.repositories.SupplierRepository;
import com.kiranastore.kirana_store.services.ProductStockService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepository productStockRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductStockServiceImpl(ProductStockRepository productStockRepository,
                                   ProductRepository productRepository,
                                   SupplierRepository supplierRepository) {
        this.productStockRepository = productStockRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public ProductStockResponse addStock(ProductStockRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + request.getSupplierId()));

        ProductStock stock = mapToEntity(request, product, supplier);
        ProductStock saved = productStockRepository.save(stock);
        return mapToResponse(saved);
    }

    @Override
    public ProductStockResponse getStockById(Long id) {
        ProductStock stock = productStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with id " + id));
        return mapToResponse(stock);
    }

    @Override
    public List<ProductStockResponse> getAllStocks() {
        return productStockRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductStockResponse updateStock(Long id, ProductStockRequest request) {
        ProductStock stock = productStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with id " + id));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + request.getSupplierId()));

        stock.setProduct(product);
        stock.setSupplier(supplier);
        stock.setPrice(request.getPrice());
        stock.setQuantity(request.getQuantity());

        ProductStock updated = productStockRepository.save(stock);
        return mapToResponse(updated);
    }

    @Override
    public void deleteStock(Long id) {
        if (!productStockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with id " + id);
        }
        productStockRepository.deleteById(id);
    }

    // Mapping methods
    private ProductStock mapToEntity(ProductStockRequest request, Product product, Supplier supplier) {
        ProductStock stock = new ProductStock();
        stock.setProduct(product);
        stock.setSupplier(supplier);
        stock.setPrice(request.getPrice());
        stock.setQuantity(request.getQuantity());
        return stock;
    }

    private ProductStockResponse mapToResponse(ProductStock stock) {
        ProductStockResponse resp = new ProductStockResponse();
        resp.setId(stock.getId());
        resp.setProductId(stock.getProduct().getId());
        resp.setSupplierId(stock.getSupplier().getId());
        resp.setPrice(stock.getPrice());
        resp.setQuantity(stock.getQuantity());
        return resp;
    }
}
