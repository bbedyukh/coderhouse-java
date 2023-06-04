package com.coderhouse.java.dto;

import java.util.*;

public class InvoiceDTO {

    private Map<String, Object> client;
    private List<Object> products;
    private Date createdAt;
    private double total;

    public Map<String, Object> getClient() {
        return client;
    }

    public List<Object> getProducts() {
        return products;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getTotal() {
        return total;
    }
}
