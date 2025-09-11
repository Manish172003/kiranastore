package com.kiranastore.kirana_store.dtos;


import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private double totalAmount;
    private Long customerId;
    private List<OrderItemResponse> items;

    // Getters and Setters
    public Long getId() { return id; }
    public OrderResponse(Long id, LocalDateTime orderDate, double totalAmount, Long customerId,
			List<OrderItemResponse> items) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.customerId = customerId;
		this.items = items;
	}
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(Long id) { this.id = id; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }
}
