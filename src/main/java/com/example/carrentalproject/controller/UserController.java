package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user/add")
    public String displayAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/user-add-form";
    }

    @PostMapping("/user/add")
    public String processAddForm(@Valid User user, BindingResult bindingResult, @RequestParam(name = "pass") String pass) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(pass)) {
            return "user/user-add-form";
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/details")
    public String carDetails(@RequestParam Long id, Model model) {
        Optional<User> carOptional = userRepository.findById(id);
        carOptional.ifPresent(u -> model.addAttribute("user", u));
        return "user/user-admin-details";
    }

    @GetMapping("/user/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "user/user-edit-form";
    }

    @PostMapping("/user/edit")
    public String processUpdateForm(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user-edit-form";
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/users")
    public String showAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 20, sort);
        model.addAttribute("users", userRepository.findAll(pageable));
        return "/user/user-list";
    }

    @PostMapping("/search")
    public String findUserByEmail(@RequestParam String search, Model model) {
        model.addAttribute("users", userRepository.findByEmailContaining(search));
        return "/user/user-search-list";
    }

    @RequestMapping("/user/delete")
    public String deleteUser(@RequestParam Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
        return "redirect:/admin/users";
    }

    @ModelAttribute("userTypes")
    public List<String> getTypesOfUser() {
        List<String> types = new ArrayList<>();
        types.add("user");
        types.add("admin");
        types.add("employee");
        return types;
    }
}
