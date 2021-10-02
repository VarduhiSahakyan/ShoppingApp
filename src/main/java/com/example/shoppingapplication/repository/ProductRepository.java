package com.example.shoppingapplication.repository;

import com.example.shoppingapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameIgnoreCase(String name);
}