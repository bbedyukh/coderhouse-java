package com.coderhouse.java.dto;

import java.util.Date;
import java.util.List;

public class InvoiceResponseDTO {

    private Long id;
    private Long clientId;
    private Date createdAt;
    private Double total;
    private List<DetailsDTO> details;

    public InvoiceResponseDTO(Long id, Long clientId, Date createdAt, Double total, List<DetailsDTO> details) {
        this.id = id;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.total = total;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Double getTotal() {
        return total;
    }

    public List<DetailsDTO> getDetails() {
        return details;
    }
}
