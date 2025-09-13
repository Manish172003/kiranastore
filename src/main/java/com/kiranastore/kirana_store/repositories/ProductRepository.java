package com.kiranastore.kirana_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByKiranaOwnerId(Long ownerId);
    List<Product> findBySupplierId(Long supplierId);
}