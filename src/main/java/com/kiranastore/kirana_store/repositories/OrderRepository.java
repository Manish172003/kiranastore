package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiranastore.kirana_store.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
