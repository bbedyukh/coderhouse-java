package com.coderhouse.java.persistences.repositories;

import com.coderhouse.java.persistences.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
