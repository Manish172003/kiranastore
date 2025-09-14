package com.kiranastore.kirana_store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.config.jwtUtil;
import com.kiranastore.kirana_store.dtos.OrderItemResponse;
import com.kiranastore.kirana_store.dtos.OrderRequest;
import com.kiranastore.kirana_store.dtos.OrderResponse;
import com.kiranastore.kirana_store.entities.Customer;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Order;
import com.kiranastore.kirana_store.entities.OrderItem;
import com.kiranastore.kirana_store.entities.Product;
import com.kiranastore.kirana_store.entities.ProductStock;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.CustomerRepository;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.OrderRepository;
import com.kiranastore.kirana_store.repositories.ProductRepository;
import com.kiranastore.kirana_store.repositories.ProductStockRepository;
import com.kiranastore.kirana_store.services.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductStockRepository productStockRepository;
    private final KiranaOwnerRepository ownerRepository;
    private final jwtUtil JWTUTIL;
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            ProductStockRepository productStockRepository,KiranaOwnerRepository ownerRepository,ProductRepository productRepository,
                            jwtUtil JWTUTIL) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productStockRepository = productStockRepository;
		this.ownerRepository = ownerRepository;
		this.JWTUTIL = JWTUTIL;
    }

//    @Override
//    public OrderResponse createOrder(OrderRequest order,HttpServletRequest request) {
//        // 🔹 Find customer
//        Customer customer = customerRepository.findById(order.getCustomerId())
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + order.getCustomerId()));
//
//        // 🔹 Find owner
//        KiranaOwner owner = ownerRepository.findById(order.getOwnerId())
//                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + order.getOwnerId()));
//
//        // 🔹 Create new Order
//        Order uporder = new Order();
//        uporder.setCustomer(customer);
//        uporder.setOwner(owner);
//        uporder.setOrderDate(LocalDateTime.now());
//
//        
//        List<OrderItem> items = order.getItems().stream().map(itemReq -> {
//            ProductStock stock = productStockRepository.findById(itemReq.getProductStockId())
//                    .orElseThrow(() -> new ResourceNotFoundException("ProductStock not found with id " + itemReq.getProductStockId()));
//
//        
//            if (stock.getQuantity() < itemReq.getQuantity()) {
//                throw new RuntimeException("Not enough stock available for product " + stock.getProduct().getName());
//            }
//
//            // reduce stock
//            stock.setQuantity(stock.getQuantity() - itemReq.getQuantity());
//
//            // create order item
//            OrderItem item = new OrderItem();
//            item.setOrder(uporder);
//            item.setProduct(stock.getProduct());  
//            item.setQuantity(itemReq.getQuantity());
//            item.setSellingPrice(stock.getPrice());
//
//            return item;
//        }).collect(Collectors.toList());
//
//        uporder.setOrderItems(items);
//
//        // 🔹 Calculate total
//        double total = items.stream()
//                .mapToDouble(i -> i.getSellingPrice() * i.getQuantity())
//                .sum();
//        uporder.setTotalAmount(total);
//
//        // 🔹 Save order
//        Order savedOrder = orderRepository.save(uporder);
//
//        return mapToResponse(savedOrder);
//    }
    
    @Override
    public OrderResponse createOrder(OrderRequest order, HttpServletRequest request) {
        // 🔹 Extract JWT token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String jwt = authHeader.substring(7);

        // 🔹 Extract email from token
        String username = JWTUTIL.extractUserName(jwt);

        // 🔹 Find owner by email
        KiranaOwner owner = ownerRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with email " + username));

        // 🔹 Find customer
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + order.getCustomerId()));

        // 🔹 Create new Order
        Order uporder = new Order();
        uporder.setCustomer(customer);
        uporder.setOwner(owner);
        uporder.setOrderDate(LocalDateTime.now());

        // 🔹 Map items
        List<OrderItem> items = order.getItems().stream().map(itemReq -> {
            ProductStock stock = productStockRepository.findById(itemReq.getProductStockId())
                    .orElseThrow(() -> new ResourceNotFoundException("ProductStock not found with id " + itemReq.getProductStockId()));

            if (stock.getQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Not enough stock available for product " + stock.getProduct().getName());
            }

            // reduce stock
            stock.setQuantity(stock.getQuantity() - itemReq.getQuantity());

            // create order item
            OrderItem item = new OrderItem();
            item.setOrder(uporder);
            item.setProduct(stock.getProduct());
            item.setQuantity(itemReq.getQuantity());
            item.setSellingPrice(stock.getPrice());

            return item;
        }).collect(Collectors.toList());

        uporder.setOrderItems(items);

        // 🔹 Calculate total
        double total = items.stream()
                .mapToDouble(i -> i.getSellingPrice() * i.getQuantity())
                .sum();
        uporder.setTotalAmount(total);

        // 🔹 Save order
        Order savedOrder = orderRepository.save(uporder);

        return mapToResponse(savedOrder);
    }




    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return mapToResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    // Mapper Methods
    private OrderResponse mapToResponse(Order order) {
        OrderResponse resp = new OrderResponse();
        resp.setId(order.getId());
        resp.setOrderDate(order.getOrderDate());
        resp.setTotalAmount(order.getTotalAmount());
        resp.setCustomerId(order.getCustomer().getId());

        List<OrderItemResponse> itemResponses = order.getOrderItems()
                .stream()
                .map(this::mapToItemResponse)
                .collect(Collectors.toList());
        resp.setItems(itemResponses);

        return resp;
    }

    private OrderItemResponse mapToItemResponse(OrderItem item) {
        OrderItemResponse resp = new OrderItemResponse();
        resp.setId(item.getId());
        resp.setQuantity(item.getQuantity());
        return resp;
    }
    
    @Override
    public List<OrderResponse> getOrdersByOwner(Long ownerId) {
        List<Order> orders = orderRepository.findByOwnerId(ownerId);
        return orders.stream()
                     .map(this::mapToResponse)
                     .collect(Collectors.toList());
    }
}
