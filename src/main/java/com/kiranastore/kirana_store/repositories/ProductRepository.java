package com.kiranastore.kirana_store.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kiranastore.kirana_store.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByKiranaOwnerId(Long ownerId);
    List<Product> findBySupplierId(Long supplierId);
}
