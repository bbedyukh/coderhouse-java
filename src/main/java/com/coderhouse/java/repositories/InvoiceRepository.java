package com.coderhouse.java.repositories;

import com.coderhouse.java.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByClientId(Long clientId);
}
