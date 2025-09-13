package com.kiranastore.kirana_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiranastore.kirana_store.entities.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByOwnerId(Long ownerId);
}
