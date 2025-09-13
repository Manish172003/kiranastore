package com.kiranastore.kirana_store.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Order;

import java.util.List;
import java.util.Optional;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface KiranaOwnerRepository extends JpaRepository<KiranaOwner, Long> {
    Optional<KiranaOwner> findByEmail(String email);
}
