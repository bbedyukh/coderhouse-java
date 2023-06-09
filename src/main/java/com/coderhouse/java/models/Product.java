package com.coderhouse.java.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String title;

    private String description;

    private Integer stock;
    private Double price;

    @JsonIgnore
    private Date deletedAt;

    @Nonnull
    @Column(unique = true, length = 50)
    private String sku;

    public Product() {
    }

    public Product(String title, String description, String sku, Double price, Integer stock) {
        this.title = title;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.stock = stock;
    }

    public static Product createWith(String title, String description, String sku, Double price, Integer stock) {
        return new Product(title, description, sku, price, stock);
    }

    public void updateWith(String title, String description, String sku, Double price, Integer stock) {
        this.title = title;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getSku() {
        return sku;
    }

    public boolean hasTitle() {
        return title != null;
    }

    public boolean hasDescription() {
        return description != null;
    }

    public boolean hasSku() {
        return sku != null;
    }

    public boolean hasPrice() {
        return price != null;
    }

    public boolean hasStock() {
        return stock != null;
    }

    public void updateStock(Long quantity) {
        stock = (int) (stock - quantity);
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
