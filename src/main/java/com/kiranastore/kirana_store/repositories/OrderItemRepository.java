package com.kiranastore.kirana_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiranastore.kirana_store.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
