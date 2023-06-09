package com.coderhouse.java.models;

import jakarta.persistence.*;

@Entity
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;
    private double price;

    @ManyToOne
    private Invoice invoice;

    @OneToOne
    private Product product;

    public InvoiceDetail(Invoice invoice, Product product, Long quantity) {
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public InvoiceDetail() {
    }

    public static InvoiceDetail createWith(Invoice invoice, Product product, Long quantity) {
        return new InvoiceDetail(invoice, product, quantity);
    }

    public Long getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }
}
