package com.coderhouse.java.services;

import com.coderhouse.java.dto.DetailsDTO;
import com.coderhouse.java.dto.InvoiceResponseDTO;
import com.coderhouse.java.dto.RequestInvoiceDTO;
import com.coderhouse.java.dto.RequestProductDTO;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.models.Invoice;
import com.coderhouse.java.models.Client;
import com.coderhouse.java.models.InvoiceDetail;
import com.coderhouse.java.repositories.InvoiceDetailRepository;
import com.coderhouse.java.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    public InvoiceResponseDTO getOne(Long invoiceId) {
        Invoice invoice = findInvoiceOrFail(invoiceId);

        List<DetailsDTO> details = invoiceDetailService.getAllBy(invoice);

        return new InvoiceResponseDTO(invoice.getId(), invoice.getClient().getId(), invoice.getCreatedAt(), invoice.getTotal(), details);
    }

    private Invoice findInvoiceOrFail(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new ApiException("Invoice not found", HttpStatus.NOT_FOUND));
    }

    public Invoice createOne(RequestInvoiceDTO requestInvoiceDTO) {
        assertRequest(requestInvoiceDTO);

        Client client = clientService.findClientOrFail(requestInvoiceDTO.getClientId());
        checkIfProductsExists(requestInvoiceDTO.getProducts());

        Invoice invoice = Invoice.createWith(client);
        invoiceRepository.save(invoice);

        List<InvoiceDetail> invoicesDetail = invoiceDetailService.createForEach(requestInvoiceDTO.getProducts(), invoice);

        Double total = invoicesDetail.stream().map(invoiceDetail -> invoiceDetail.getPrice() * invoiceDetail.getQuantity()).reduce(0D, Double::sum);
        invoice.setTotal(total);
        invoiceRepository.save(invoice);

        return invoice;
    }

    private void checkIfProductsExists(List<RequestProductDTO> products) {
        products.forEach(product -> {
            Long productId = product.getProductId();
            productService.findProductOrFail(productId);
        });
    }

    private void assertRequest(RequestInvoiceDTO requestInvoiceDTO) {
        if (!requestInvoiceDTO.hasClientId() || !requestInvoiceDTO.hasProducts()) throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }
}
