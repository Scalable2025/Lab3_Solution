package com.example.Lab_4_Testing.service;

import java.util.*;

import com.example.Lab_4_Testing.Models.Product;
import com.example.Lab_4_Testing.Models.User;
import com.example.Lab_4_Testing.repository.CartRepository;
import com.example.Lab_4_Testing.repository.ProductRepository;
import com.example.Lab_4_Testing.repository.TransactionRepository;
import com.example.Lab_4_Testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabasePopulatorService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public DatabasePopulatorService(UserRepository userRepository,
                                    CartRepository cartRepository,
                                    ProductRepository productRepository,
                                    TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void populateDatabase() {
        // 1. Insert 10 sample users
        // for (int i = 1; i <= 10; i++) {
        //     User user = new User();
        //     user.setName("User"+i);
        //     user.setEmail("user"+i+"@gmail.com");
        //     user.setAge(20+i);
        //     userRepository.save(user);
            
        // }

        // 2. Retrieve users from the database (assuming email is unique)
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = userRepository.findByEmail("user" + i + "@gmail.com");
            users.add(user);
        }
        

        // 3. Insert a cart for each user
        for (User user : users) {
            cartRepository.insertCart(user.getId());
        }

        // 4. Retrieve the generated cart IDs for each user
        Map<UUID, Integer> userCartIds = new HashMap<>();
        for (User user : users) {
            Integer cartId = cartRepository.findCartIdByUserId(user.getId());
            userCartIds.put(user.getId(), cartId);
        }

        // 5. Insert 10 sample products
        for (int i = 1; i <= 10; i++) {
            String productName = "Product " + i;
            double price = 10.0 + i;  // Example price value
            Product product=new Product(productName,price);
            productRepository.save(product);
        }

        // 6. Associate each user's cart with a product.
        // Here we assign product with id = i to the cart for user i.
        int productId = 1;
        for (User user : users) {
            int cartId = userCartIds.get(user.getId());
            cartRepository.insertCartProduct(cartId, productId);
            productId++;
        }

        // 7. Insert one transaction per user (total 10 transactions)
        for (User user : users) {
            int cartId = userCartIds.get(user.getId());
            String description = "Transaction for " + user.getName();
            double amount = 100.0 + user.getAge(); // Example amount
            String date = "2025-02-22"; // Fixed date for simplicity
            transactionRepository.insertTransaction(description, amount, date, cartId, user.getId());
        }
    }
}
