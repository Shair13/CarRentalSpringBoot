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
@SessionAttributes("user")
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "home/home";
    }

    @GetMapping(path = "/registration")
    public String displayAddForm(Model model) {
        model.addAttribute("user", new User());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String processAddForm(@Valid User user, BindingResult bindingResult, @RequestParam(name = "pass") String pass) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(pass)) {
            return "home/registration";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String displayLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && "user".equals(user.getType())) {
            return "redirect:/user/dashboard";
        }
        return "home/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "redirect:/login";
        }
        User user = userOptional.get();
        String typeOfUser = user.getType();
        if ("user".equals(typeOfUser) && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/user/dashboard";
        }
        if (typeOfUser.equals("employee") && user.getPassword().equals(password)) {
            return "";
        }
        if ("admin".equals(typeOfUser) && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/admin/dashboard";
        }
        return "redirect:/login";
    }

}
