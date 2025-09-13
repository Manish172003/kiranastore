package com.kiranastore.kirana_store.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    public Customer(Long id, String name, String phone, String email, String address, KiranaOwner owner,
			List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.owner = owner;
		this.orders = orders;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	private String phone;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private KiranaOwner owner;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public KiranaOwner getOwner() { return owner; }
    public void setOwner(KiranaOwner owner) { this.owner = owner; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
