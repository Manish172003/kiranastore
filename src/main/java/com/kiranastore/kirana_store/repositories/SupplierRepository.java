package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiranastore.kirana_store.entities.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByKiranaOwnerId(Long ownerId);
}

