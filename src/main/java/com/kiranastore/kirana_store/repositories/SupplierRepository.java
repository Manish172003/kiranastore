package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.Supplier;
import java.util.List;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByOwner_Id(Long ownerId);
}
