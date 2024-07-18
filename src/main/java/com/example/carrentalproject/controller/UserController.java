package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.User;
import com.example.carrentalproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

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
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/details")
    public String carDetails(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/user-admin-details";
    }

    @GetMapping("/user/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/user-edit-form";
    }

    @PostMapping("/user/edit")
    public String processUpdateForm(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/user-edit-form";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("users", userService.findAll(page));
        return "/user/user-list";
    }

    @PostMapping("/search")
    public String findUserByEmail(@RequestParam String search, Model model) {
        model.addAttribute("users", userService.findUserByEmailFragment(search));
        return "/user/user-search-list";
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
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
