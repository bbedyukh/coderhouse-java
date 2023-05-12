package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
    List<InvoiceDetail> findAllByInvoiceId(Long invoiceId);
}
