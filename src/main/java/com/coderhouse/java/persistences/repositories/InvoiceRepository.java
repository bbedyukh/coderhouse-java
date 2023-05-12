package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
