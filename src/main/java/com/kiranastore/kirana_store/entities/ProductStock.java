package com.kirana.store.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_stocks")
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private int quantity;

    // Each stock belongs to one Product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Each stock is supplied by one Supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    // Default constructor (required by JPA)
    public ProductStock() {
    }

    // All-args constructor
    public ProductStock(Long id, double price, int quantity, Product product, Supplier supplier) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.supplier = supplier;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}
