package com.kiranastore.kirana_store.dtos;


public class OrderItemRequest {
    private Long productStockId;
    private int quantity;
   
    public Long getProductStockId() { return productStockId; }
    public OrderItemRequest(Long productStockId, int quantity) {
		super();
		this.productStockId = productStockId;
		this.quantity = quantity;
	}
    
	public OrderItemRequest() {
		super();
	}
	public void setProductStockId(Long productStockId) { this.productStockId = productStockId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
