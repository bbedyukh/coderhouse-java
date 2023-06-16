package com.coderhouse.java.services;

import com.coderhouse.java.dto.DetailsDTO;
import com.coderhouse.java.dto.InvoiceResponseDTO;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.models.Client;
import com.coderhouse.java.models.Invoice;
import com.coderhouse.java.repositories.ClientRepository;
import com.coderhouse.java.repositories.InvoiceDetailRepository;
import com.coderhouse.java.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getOne(Long clientId) {
        return findClientOrFail(clientId);
    }

    public Client findClientOrFail(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new ApiException("Client #" + clientId + " not found", HttpStatus.NOT_FOUND));
    }

    public Client createOne(Client client) {
        assertRequest(client);
        checkIfClientExists(client);
        Client instance = Client.createWith(client.getFirstname(), client.getLastname(), client.getDni());
        return clientRepository.save(instance);
    }

    private void checkIfClientExists(Client client) {
        boolean exists = clientRepository.findByDni(client.getDni()).isPresent();
        if (exists) throw new ApiException("Client already exists", HttpStatus.BAD_REQUEST);
    }

    public Client updateOne(Client client, Long clientId) {
        assertRequest(client);
        Client instance = findClientOrFail(clientId);
        instance.updateWith(client.getFirstname(), client.getLastname(), client.getDni());
        return clientRepository.save(instance);
    }

    private static void assertRequest(Client client) {
        if (!client.hasFirstName() || !client.hasLastName() || !client.hasDNI())
            throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }

    public void deleteOne(Long clientId) {
        Client client = findClientOrFail(clientId);
        clientRepository.delete(client);
    }

    public List<InvoiceResponseDTO> getInvoices(Long clientId) {
        Client client = findClientOrFail(clientId);
        List<InvoiceResponseDTO> invoiceResponse = new ArrayList<>();

        List<Invoice> invoices = invoiceRepository.findAllByClientId(client.getId());

        invoices.forEach(invoice -> {

            List<DetailsDTO> details = invoiceDetailRepository.findAllByInvoiceId(invoice.getId())
                    .stream()
                    .map(invoiceDetail -> new DetailsDTO(invoiceDetail.getProduct().getTitle(), invoiceDetail.getProduct().getDescription(), invoiceDetail.getProduct().getSku(), invoiceDetail.getPrice(), invoiceDetail.getQuantity()))
                    .toList();

            invoiceResponse.add(new InvoiceResponseDTO(invoice.getId(), invoice.getClient().getId(), invoice.getCreatedAt(), invoice.getTotal(), details));

        });

        return invoiceResponse;
    }
}
