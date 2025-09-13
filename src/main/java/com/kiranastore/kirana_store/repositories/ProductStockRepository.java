package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.ProductStock;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
