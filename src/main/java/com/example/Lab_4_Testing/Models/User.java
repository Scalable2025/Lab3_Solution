package com.example.Lab_4_Testing.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private int id;
    private String Name;
    private String Email;
    private int age;

    @OneToOne(mappedBy = "user_id")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    @JoinColumn(name = "transactionID", referencedColumnName = "id")
    private List<Transaction> transactions;

    public User(String name, String email, int age) {
        Name = name;
        Email = email;
        this.age = age;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }
}
