package com.coderhouse.java.services;

import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.persistences.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice getOne(Long invoiceId) {
        return findInvoiceOrFail(invoiceId);
    }

    private Invoice findInvoiceOrFail(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new ApiException("Invoice not found", HttpStatus.NOT_FOUND));
    }

    public Invoice createOne(Invoice invoice) {
        validateProperties(invoice);
    }

    private void validateProperties(Invoice invoice) {
        if (!invoice.hasClient() || !invoice.has() || !invoice.hasDNI())
            throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }
}
