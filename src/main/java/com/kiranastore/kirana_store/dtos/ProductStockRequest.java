package com.kiranastore.kirana_store.dtos;


public class ProductStockRequest {
    private Long productId;
    private Long supplierId;
    private double price;
    private int quantity;

    public ProductStockRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductStockRequest(Long productId, Long supplierId, double price, int quantity) {
		super();
		this.productId = productId;
		this.supplierId = supplierId;
		this.price = price;
		this.quantity = quantity;
	}
	// Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
