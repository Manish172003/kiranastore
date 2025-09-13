package com.kiranastore.kirana_store.dtos;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private Long ownerId;
    private String ownerName;
    private List<ProductStockResponse> productStocks = new ArrayList<>();
    
    

	public ProductResponse(Long id, String name, Long ownerId, String ownerName,
			List<ProductStockResponse> productStocks) {
		super();
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.productStocks = productStocks;
	}
	public ProductResponse() {
		
	}
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
	public List<ProductStockResponse> getProductStocks() {
		return productStocks;
	}
	public void setProductStocks(List<ProductStockResponse> productStocks) {
		this.productStocks = productStocks;
	}
    
    

  
}
