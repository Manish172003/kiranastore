package com.kiranastore.kirana_store.dtos;


public class ProductStockResponse {
    private Long id;
    private Long productId;
    private Long supplierId;
    private double price;
    private int quantity;

    public ProductStockResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductStockResponse(Long id, Long productId, Long supplierId, double price, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.supplierId = supplierId;
		this.price = price;
		this.quantity = quantity;
	}
	// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

