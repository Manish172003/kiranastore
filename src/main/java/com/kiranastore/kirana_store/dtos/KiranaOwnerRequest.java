package com.kiranastore.kirana_store.dtos;



public class KiranaOwnerRequest {
    private String name;
    private String email;
    private String password;
    private String storeName;
    private String phone;
    private String address;

    // Getters and Setters
    public String getName() { return name; }
    public KiranaOwnerRequest(String name, String email, String password, String storeName, String phone,
			String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
	}
    
	public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
