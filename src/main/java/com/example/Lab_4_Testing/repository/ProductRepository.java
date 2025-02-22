package com.example.Lab_4_Testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Lab_4_Testing.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    

}
