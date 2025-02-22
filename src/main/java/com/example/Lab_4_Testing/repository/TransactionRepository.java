package com.example.Lab_4_Testing.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Lab_4_Testing.Models.Transaction;

import jakarta.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transactions (description, amount, date, cartID, user_id) VALUES (:description, :amount, :date, :cartId, :userId)", nativeQuery = true)
    void insertTransaction(@Param("description") String description,
                           @Param("amount") double amount,
                           @Param("date") String date,
                           @Param("cartId") int cartId,
                           @Param("userId") UUID userId);
    

}
