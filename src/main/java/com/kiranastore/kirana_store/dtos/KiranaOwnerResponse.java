package com.kiranastore.kirana_store.dtos;



public class KiranaOwnerResponse {
    private Long id;
    private String name;
    private String email;
    private String storeName;
    private String phone;
    private String address;
    
   
    public Long getId() { return id; }
    public KiranaOwnerResponse(Long id, String name, String email, String storeName, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
	}
    
	public KiranaOwnerResponse() {
	
	}
	public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
