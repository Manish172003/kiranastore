package com.kiranastore.kirana_store.dtos;



public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long ownerId;
    private Long supplierId;
   
    public ProductRequest(String name, String description, Double price, Integer quantity, Long ownerId,
			Long supplierId) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.ownerId = ownerId;
		this.supplierId = supplierId;
	}
    
    
	public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
}
