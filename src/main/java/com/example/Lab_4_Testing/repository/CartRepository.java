package com.example.Lab_4_Testing.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Lab_4_Testing.Models.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carts(user_id) values(:userId)", nativeQuery = true)
    void insertCart(UUID userId);

    @Query(value = "SELECT id FROM carts WHERE user_id = :userId", nativeQuery = true)
    Integer findCartIdByUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart_product (cart_id, product_id) VALUES (:cartId, :productId)", nativeQuery = true)
    void insertCartProduct(@Param("cartId") int cartId, @Param("productId") int productId);


}
