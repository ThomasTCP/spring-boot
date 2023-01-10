package com.example.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productDetail")
public class Product_detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "description", nullable = false)
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Product_detail(long id, String brand, String description, Product product) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.product = product;
    }

    public Product_detail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
