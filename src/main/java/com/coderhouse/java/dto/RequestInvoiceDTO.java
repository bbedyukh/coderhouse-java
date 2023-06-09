package com.coderhouse.java.dto;

import java.util.*;

public class RequestInvoiceDTO {

    private Long clientId;
    private List<RequestProductDTO> products;

    public Long getClientId() {
        return clientId;
    }

    public List<RequestProductDTO> getProducts() {
        return products;
    }

    public boolean hasClientId() {
        return clientId != null;
    }

    public boolean hasProducts() {
        return products.size() > 0 && products.stream().allMatch(product -> product.hasProductId() && product.hasQuantity());
    }
}
