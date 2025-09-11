package com.kiranastore.kirana_store.dtos;

public class OrderItemResponse {
    private Long id;
    private Long productStockId;
    private int quantity;
    private double price;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductStockId() { return productStockId; }
    public void setProductStockId(Long productStockId) { this.productStockId = productStockId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
