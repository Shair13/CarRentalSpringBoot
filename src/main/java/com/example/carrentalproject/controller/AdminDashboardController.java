package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @RequestMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "acc-admin/admin-dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
