package com.kiranastore.kirana_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    List<Supplier> findAllByOwner(KiranaOwner owner);
}