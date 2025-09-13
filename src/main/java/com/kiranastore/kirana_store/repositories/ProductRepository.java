package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kiranastore.kirana_store.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Corrected: entity field is 'owner', so JPA expects this method name
    List<Product> findByOwnerId(Long ownerId);
}
