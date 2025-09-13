package com.kiranastore.kirana_store.dtos;

import java.util.List;

public class OrderRequest {
    private Long customerId;
    private Long ownerId;
    private List<OrderItemRequest> items;
    public OrderRequest(Long customerId, Long ownerId, List<OrderItemRequest> items) {
		super();
		this.customerId = customerId;
		this.ownerId = ownerId;
		this.items = items;
	}

	public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
