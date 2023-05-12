package com.coderhouse.java.persistences;

import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Invoice;
import com.coderhouse.java.persistences.models.InvoiceDetail;
import com.coderhouse.java.persistences.models.Product;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import com.coderhouse.java.persistences.repositories.InvoiceDetailRepository;
import com.coderhouse.java.persistences.repositories.InvoiceRepository;
import com.coderhouse.java.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${initData:false}")
    private String initData;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initData.equals("false")) return;

        Client client = Client.createWith("Bogdan", "Bedyukh", "12345678");
        clientRepository.save(client);

        Product cocaCola = Product.createWith("Coca Cola 500cc", "CC500", 199.99, 99);
        productRepository.save(cocaCola);

        Product pepsi = Product.createWith("Pepsi 500cc", "P500", 189.99, 99);
        productRepository.save(pepsi);

        Invoice invoice = Invoice.createWith(client);
        invoiceRepository.save(invoice);

        InvoiceDetail invoiceDetail = InvoiceDetail.createWith(invoice, cocaCola, 3);
        invoiceDetailRepository.save(invoiceDetail);

        InvoiceDetail invoiceDetail2 = InvoiceDetail.createWith(invoice, pepsi, 7);
        invoiceDetailRepository.save(invoiceDetail2);

        List<InvoiceDetail> invoicesDetail = invoiceDetailRepository.findAllByInvoiceId(invoice.getId());
        double total = invoicesDetail.stream().map(iDetail -> iDetail.getQuantity() * iDetail.getPrice()).reduce(0D, Double::sum);

        invoice.setTotal(total);
        invoiceRepository.save(invoice);

        initData = "false";
    }

}