package com.example.carrentalproject.controller;

import com.example.carrentalproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
        return userService.checkUser(session, model);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userService.logout(session);
        return "redirect:/";
    }

}
