package com.coderhouse.java.services;

import com.coderhouse.java.dto.DetailsDTO;
import com.coderhouse.java.dto.RequestProductDTO;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.models.Invoice;
import com.coderhouse.java.models.InvoiceDetail;
import com.coderhouse.java.models.Product;
import com.coderhouse.java.repositories.InvoiceDetailRepository;
import com.coderhouse.java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceDetailService {

    @Autowired
    private ProductService productService;

    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    ProductRepository productRepository;

    public List<InvoiceDetail> createForEach(List<RequestProductDTO> products, Invoice invoice) {
        List<InvoiceDetail> invoicesDetail = new ArrayList<>();

        products.forEach(product -> {
            Long productId = product.getProductId();
            Long quantity = product.getQuantity();

            Product instance = productService.findProductOrFail(productId);
            checkStockOrFail(quantity, instance);
            instance.updateStock(quantity);
            productRepository.save(instance);

            InvoiceDetail invoiceDetail = InvoiceDetail.createWith(invoice, instance, quantity);

            invoicesDetail.add(invoiceDetailRepository.save(invoiceDetail));
        });

        return invoicesDetail;
    }

    private static void checkStockOrFail(Long quantity, Product instance) {
        if (quantity > instance.getStock()) throw new ApiException("Stock " + quantity + " not available", HttpStatus.BAD_REQUEST);
    }

    public List<DetailsDTO> getAllBy(Invoice invoice) {
        return invoiceDetailRepository.findAllByInvoiceId(invoice.getId())
                .stream()
                .map(invoiceDetail -> new DetailsDTO(invoiceDetail.getProduct().getTitle(), invoiceDetail.getProduct().getDescription(), invoiceDetail.getProduct().getSku(), invoiceDetail.getPrice(), invoiceDetail.getQuantity()))
                .toList();
    }
}
