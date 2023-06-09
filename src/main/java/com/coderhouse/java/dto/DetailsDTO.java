package com.coderhouse.java.dto;

public class DetailsDTO {

    private String title;
    private String description;
    private String sku;
    private Double price;
    private Long quantity;

    public DetailsDTO(String title, String description, String sku, Double price, Long quantity) {
        this.title = title;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSku() {
        return sku;
    }

    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }
}
