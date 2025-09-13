package com.kiranastore.kirana_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.entities.Supplier;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByOwner(KiranaOwner owner);
    List<Product> findBySupplier(Supplier supplier);
}