package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Role;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.RoleRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
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
        userService.editUser(user);
        return "redirect:/admin/users";
    }

//    @RequestMapping("/users")
//    public String showAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        PageRequest pageable = PageRequest.of(page, 20, sort);
//        model.addAttribute("users", userRepository.findAll(pageable));
//        return "/user/user-list";
//    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
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

    @ModelAttribute("userRoles")
    public List<Role> getTypesOfUser() {
       return roleRepository.findAll();
    }
}
