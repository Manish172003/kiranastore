package com.kiranastore.kirana_store.services;
import java.util.List;

import com.kiranastore.kirana_store.dtos.OrderRequest;
import com.kiranastore.kirana_store.dtos.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();
    void deleteOrder(Long id);
}
