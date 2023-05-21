package com.coderhouse.java.services;

import com.coderhouse.java.dto.ClientDTO;
import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client.getFirstName(), client.getLastName(), getAge(client))).toList();
    }

    private static int getAge(Client client) {
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        return today.get(Calendar.YEAR) - client.getBirthDate().get(Calendar.YEAR);
    }

}
