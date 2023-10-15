package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.User;
import com.example.carrentalproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setFirstName("Czarek");
        user.setLastName("Pieczarek");
        user.setEmail("pieczarek@wp.pl");
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }
}
