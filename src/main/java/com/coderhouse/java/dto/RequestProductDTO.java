package com.coderhouse.java.dto;

public class RequestProductDTO {

    private Long productId;
    private Long quantity;

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public boolean hasProductId() {
        return productId != null;
    }

    public boolean hasQuantity() {
        return quantity != null;
    }
}
