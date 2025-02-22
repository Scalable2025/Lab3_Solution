package com.example.Lab_4_Testing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Lab_4_Testing.Models.Cart;
import com.example.Lab_4_Testing.Models.Product;
import com.example.Lab_4_Testing.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @GetMapping("/getCarts/{productId}")
    public List<Cart> getCartsByProductId(@PathVariable int productId) {
        return productRepository.findById(productId).get().getCarts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productRepository.findById(productId).get();
    }
    
    
}
