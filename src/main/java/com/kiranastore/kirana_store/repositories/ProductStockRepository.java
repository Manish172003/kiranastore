package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiranastore.kirana_store.entities.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
