//package com.kiranastore.kirana_store.services.impl;
//
//import org.springframework.stereotype.Service;
//import com.kiranastore.kirana_store.dtos.OrderItemResponse;
//import com.kiranastore.kirana_store.dtos.OrderRequest;
//import com.kiranastore.kirana_store.dtos.OrderResponse;
//import com.kiranastore.kirana_store.entities.Customer;
//import com.kiranastore.kirana_store.entities.Order;
//import com.kiranastore.kirana_store.entities.OrderItem;
//import com.kiranastore.kirana_store.entities.ProductStock;
//import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
//import com.kiranastore.kirana_store.repositories.CustomerRepository;
//import com.kiranastore.kirana_store.repositories.OrderRepository;
//import com.kiranastore.kirana_store.repositories.ProductStockRepository;
//import com.kiranastore.kirana_store.services.OrderService;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    private final OrderRepository orderRepository;
//    private final CustomerRepository customerRepository;
//    private final ProductStockRepository productStockRepository;
//
//    public OrderServiceImpl(OrderRepository orderRepository,
//                            CustomerRepository customerRepository,
//                            ProductStockRepository productStockRepository) {
//        this.orderRepository = orderRepository;
//        this.customerRepository = customerRepository;
//        this.productStockRepository = productStockRepository;
//    }
//
//    @Override
//    public OrderResponse createOrder(OrderRequest request) {
//        Customer customer = customerRepository.findById(request.getCustomerId())
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + request.getCustomerId()));
//
//        Order order = new Order();
//        order.setCustomer(customer);
//        order.setOrderDate(LocalDateTime.now());
//
//        double total = 0.0;
//
//        List<OrderItem> items = request.getItems().stream().map(itemReq -> {
//            ProductStock stock = productStockRepository.findById(itemReq.getProductStockId())
//                    .orElseThrow(() -> new ResourceNotFoundException("ProductStock not found with id " + itemReq.getProductStockId()));
//
//            if (stock.getQuantity() < itemReq.getQuantity()) {
//                throw new RuntimeException("Not enough stock available for product " + stock.getProduct().getName());
//            }
//
//            stock.setQuantity(stock.getQuantity() - itemReq.getQuantity());
//
//            OrderItem item = new OrderItem();
//            item.setOrder(order);
//            item.setProductStock(stock);
//            item.setQuantity(itemReq.getQuantity());
//            item.setPrice(stock.getPrice());
//
//            total += stock.getPrice() * itemReq.getQuantity();
//
//            return item;
//        }).collect(Collectors.toList());
//
//        order.setOrderItems(items);
//        order.setTotalAmount(total);
//
//        Order savedOrder = orderRepository.save(order);
//        return mapToResponse(savedOrder);
//    }
//
//    @Override
//    public OrderResponse getOrderById(Long id) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
//        return mapToResponse(order);
//    }
//
//    @Override
//    public List<OrderResponse> getAllOrders() {
//        return orderRepository.findAll()
//                .stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteOrder(Long id) {
//        if (!orderRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Order not found with id " + id);
//        }
//        orderRepository.deleteById(id);
//    }
//
//    // Mapper Methods
//    private OrderResponse mapToResponse(Order order) {
//        OrderResponse resp = new OrderResponse();
//        resp.setId(order.getId());
//        resp.setOrderDate(order.getOrderDate());
//        resp.setTotalAmount(order.getTotalAmount());
//        resp.setCustomerId(order.getCustomer().getId());
//
//        List<OrderItemResponse> itemResponses = order.getOrderItems()
//                .stream()
//                .map(this::mapToItemResponse)
//                .collect(Collectors.toList());
//        resp.setItems(itemResponses);
//
//        return resp;
//    }
//
//    private OrderItemResponse mapToItemResponse(OrderItem item) {
//        OrderItemResponse resp = new OrderItemResponse();
//        resp.setId(item.getId());
//        resp.setProductStockId(item.getProductStock().getId());
//        resp.setQuantity(item.getQuantity());
//        resp.setPrice(item.getPrice());
//        return resp;
//    }
//}
