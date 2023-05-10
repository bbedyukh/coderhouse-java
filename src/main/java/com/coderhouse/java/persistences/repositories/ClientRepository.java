package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
