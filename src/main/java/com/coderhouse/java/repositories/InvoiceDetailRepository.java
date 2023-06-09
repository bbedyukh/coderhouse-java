package com.coderhouse.java.repositories;

import com.coderhouse.java.models.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    List<InvoiceDetail> findAllByInvoiceId(Long invoiceId);

    Optional<InvoiceDetail> findByProductId(Long productId);
}
