package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiranastore.kirana_store.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
