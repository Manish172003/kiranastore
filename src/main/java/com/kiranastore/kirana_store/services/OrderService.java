package com.kiranastore.kirana_store.services;
import java.util.List;

import com.kiranastore.kirana_store.dtos.OrderRequest;
import com.kiranastore.kirana_store.dtos.OrderResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {
    OrderResponse createOrder(OrderRequest order,HttpServletRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();
    void deleteOrder(Long id);
    public List<OrderResponse> getOrdersByOwner(Long ownerId);
}
