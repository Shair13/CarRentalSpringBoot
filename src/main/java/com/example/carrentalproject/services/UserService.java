package com.example.carrentalproject.services;


import com.example.carrentalproject.model.User;

public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);
}
