package com.kiranastore.kirana_store.dtos;

public class SupplierRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long ownerId; 
    private String ownerName;
    

    public SupplierRequest() {
		super();
		
	}
	public SupplierRequest(String name, String email, String phone, String address, Long ownerId) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.ownerId = ownerId;
	}
	// Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
    
    
}
