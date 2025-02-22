package com.example.Lab_4_Testing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Lab_4_Testing.Models.User;
import com.example.Lab_4_Testing.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return user.get();
        
    }
    public List<User> findAllUsers() {
        // return userRepository.findAll();
        return userRepository.findAllUsers();
    }
    // public Cart getCartByUserId(UUID userId){
    //     return userRepository.getCartByUserId(userId);

    // }

    public void populateRandom(){
        for(int i=0;i<10;i++){
            User user = new User();
            user.setName("User"+i);
            user.setEmail("user"+i+"@gmail.com");
            user.setAge(20+i);
            userRepository.save(user);
        }

    }
    
    public String updateUser(String name, String email, int age, UUID userId) {
        try{
            userRepository.updateUser(name, email, age, userId);
            return "User updated";
        }
        catch(Exception e){
            return "User not found";
        }
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteUser(userId);
    }


}
