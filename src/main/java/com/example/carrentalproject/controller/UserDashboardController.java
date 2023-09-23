package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

    private final UserRepository userRepository;

    public UserDashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
//        model.addAttribute("lastFive", rentRepository.findLast5ByCustomer(user));
        model.addAttribute("user", user);

        return "acc-user/dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("edit/data")
    public String displayUpdateUserForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        Optional<User> userOptional = userRepository.findById(user.getId());
        userOptional.ifPresent(u -> model.addAttribute("user", u));
        return "acc-user/edit-data";
    }

    @PostMapping("/edit/data")
    public String processUpdateUserForm(@Valid User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/user/edit/data";
        }
        userRepository.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
        session.setAttribute("user", user);
        return "redirect:/user/dashboard";
    }

    @GetMapping("edit/password")
    public String displayUpdateUserPassForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "acc-user/edit-password";
    }

    @PostMapping("/edit/password")
    public String processUpdateUserPassForm(@RequestParam String pass1,
                                            @RequestParam String pass2,
                                            HttpSession session) {
        if ("".equals(pass1) || !pass1.equals(pass2)){
            return "redirect:/user/edit/password";
        }
        User user = (User) session.getAttribute("user");
        userRepository.updatePassword(pass1, user.getId());
        session.setAttribute("user", user);
        return "redirect:/user/dashboard";
    }


}
