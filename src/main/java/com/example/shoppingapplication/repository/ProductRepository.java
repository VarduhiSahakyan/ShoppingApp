package com.example.shoppingapplication.repository;

import com.example.shoppingapplication.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String name);

}