package com.example.Lab_4_Testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Lab_4_Testing.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    Product findByPrice(double price);
    Product findById(int id);
    Product findByNameAndPrice(String name, double price);
    Product findByNameAndId(String name, int id);
    Product findByPriceAndId(double price, int id);
    Product findByNameAndPriceAndId(String name, double price, int id);

}
