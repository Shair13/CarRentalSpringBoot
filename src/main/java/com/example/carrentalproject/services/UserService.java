package com.example.carrentalproject.services;


import com.example.carrentalproject.model.User;

public interface UserService {

    User findByUserName(String name);
    void saveUser(User user);
    void editUser(User user);

}
