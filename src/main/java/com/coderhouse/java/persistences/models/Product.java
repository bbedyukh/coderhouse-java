package com.coderhouse.java.persistences.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String name;

    private double price;
    private int stock;

    @Nonnull
    @Column(unique = true, length = 50)
    private String code;

    public Product() {
    }

    public Product(String name, String code, double price, int stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.stock = stock;
    }

    public static Product createWith(String name, String code, double price, int stock) {
        return new Product(name, code, price, stock);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

}
