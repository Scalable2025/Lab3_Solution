package com.example.Lab_4_Testing.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.Lab_4_Testing.Models.User;

import jakarta.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Query(value = "SELECT * FROM \"users\"", nativeQuery = true)
    public List<User> findAllUsers();

    @Modifying
    @Transactional
    @Query(value = "UPDATE \"users\" SET name=:name, email=:email, age=:age WHERE id=:userId", nativeQuery = true)
    public void updateUser(@Param(value = "name") String name, @Param(value= "email") 
    String email, @Param(value="age") int age,@Param(value="userId") UUID userId);

    @Procedure(procedureName = "delete_user")
    public void deleteUser(@Param(value = "userId") UUID userId);

}
