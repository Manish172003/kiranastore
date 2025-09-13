package com.kiranastore.kirana_store.dtos;


public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private Long ownerId;
   
    public ProductRequest(String name, String description, Double price, Integer quantity, Long ownerId,
    		String category) {
		super();
		this.name = name;
		this.description = description;
		this.ownerId = ownerId;
        this.category = category;
	}
    
	public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) { this.description = description; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    
}
