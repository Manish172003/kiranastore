package com.kiranastore.kirana_store.dtos;

import java.util.ArrayList;
import java.util.List;

public class KiranaOwnerResponse {
    private Long id;
    private String name;
    private String email;
    private String storeName;
    private String phone;
    private String address;
    private List<CustomerResponse> customers = new ArrayList<>();
    private List<ProductResponse> products  = new ArrayList<>();;
    private List<SupplierResponse> suppliers =  new ArrayList<>();;
   
    public Long getId() { return id; }
   
	public KiranaOwnerResponse(Long id, String name, String email, String storeName, String phone, String address,
			List<CustomerResponse> customers, List<ProductResponse> products, List<SupplierResponse> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
		this.customers = customers;
		this.products = products;
		this.suppliers = suppliers;
	}

	public List<CustomerResponse> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerResponse> customers) {
		this.customers = customers;
	}

	public KiranaOwnerResponse(Long id, String name, String email, String storeName, String phone, String address,
			List<CustomerResponse> customers, List<SupplierResponse> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.storeName = storeName;
		this.phone = phone;
		this.address = address;
		this.customers = customers;
		this.suppliers = suppliers;
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
    
    public List<SupplierResponse> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<SupplierResponse> suppliers) {
		this.suppliers = suppliers;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	
	
	
	
}
