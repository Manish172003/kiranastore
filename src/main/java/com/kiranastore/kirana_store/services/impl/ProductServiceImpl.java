package com.kiranastore.kirana_store.services.impl;

import org.springframework.stereotype.Service;
import com.kiranastore.kirana_store.dtos.ProductRequest;
import com.kiranastore.kirana_store.dtos.ProductResponse;
import com.kiranastore.kirana_store.dtos.ProductStockResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.ProductRepository;
import com.kiranastore.kirana_store.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final KiranaOwnerRepository ownerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              KiranaOwnerRepository ownerRepository) {
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        KiranaOwner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + request.getOwnerId()));

        Product product = mapToEntity(request, owner);
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
        return productRepository.findByOwnerId(ownerId)
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
    
    public List<ProductResponse> getAllProducts() {
    	return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    // 🔹 Mapping functions
    private Product mapToEntity(ProductRequest request, KiranaOwner owner) {
        Product product = new Product();
        product.setName(request.getName());
//        product.setCategory(request.getCategory());
        product.setOwner(owner);
        return product;
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setOwnerId(product.getOwner().getId());
        response.setOwnerName(product.getOwner().getName());
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

            response.setProductStocks(stockResponses);
        }

        return response;
    }
}
 