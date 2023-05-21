package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
