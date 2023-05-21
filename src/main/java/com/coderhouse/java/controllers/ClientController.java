package com.coderhouse.java.controllers;

import com.coderhouse.java.dto.ClientDTO;
import com.coderhouse.java.dto.ResponseHandler;
import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<ClientDTO> clients = clientService.getAll();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseHandler.generate("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
