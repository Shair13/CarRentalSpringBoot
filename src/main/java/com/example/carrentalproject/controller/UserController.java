package com.example.carrentalproject.controller;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    // User
//
//    @GetMapping("udashboard/edit")
//    public String displayUpdateUserForm(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
//        User user = (User) session.getAttribute("user");
//        Optional<User> userOptional = userRepository.findById(user.getId());
//        userOptional.ifPresent(u -> model.addAttribute("user", u));
//        return "user/user-update-form";
//    }
//
//    @PostMapping("/udashboard/edit")
//    public String processUpdateUserForm(@Valid User user, BindingResult bindingResult, HttpSession session) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/user/udashboard/edit";
//        }
//        userRepository.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
//        session.setAttribute("user", user);
//        return "redirect:/udashboard";
//    }
//
//
//    @GetMapping("udashboard/editpass")
//    public String displayUpdateUserPassForm(HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
////        User user = (User) session.getAttribute("user");
////        Optional<User> userOptional = userRepository.findById(user.getId());
////        userOptional.ifPresent(u -> model.addAttribute("user", u));
//        return "user/user-update-password-form";
//    }
//
//    @PostMapping("/udashboard/editpass")
//    public String processUpdateUserPassForm(@RequestParam String pass1,
//                                            @RequestParam String pass2,
//                                            HttpSession session) {
//        if ("".equals(pass1) || !pass1.equals(pass2)){
//            return "redirect:/user/udashboard/editpass";
//        }
//        User user = (User) session.getAttribute("user");
//        userRepository.updatePassword(pass1, user.getId());
//        session.setAttribute("user", user);
//        return "redirect:/udashboard";
//    }


    @GetMapping(path = "/registration")
    public String displayAddForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add-form";
    }

    @PostMapping("/registration")
    public String processAddForm(@Valid User user, BindingResult bindingResult, @RequestParam(name = "pass") String pass) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(pass)) {
            return "user/add-form";
        }
        userRepository.save(user);
        return "redirect:/user/showAll";
    }

//    @GetMapping("/update")
//    public String displayUpdateForm(@RequestParam Long id, Model model) {
//        Optional<User> userOptional = userRepository.findById(id);
//        userOptional.ifPresent(user -> model.addAttribute("user", user));
//        return "user/update-form";
//    }
//
//    @PostMapping("/update")
//    public String processUpdateForm(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user/update-form";
//        }
//        userRepository.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
//        return "redirect:/user/showAll";
//    }
//
//    @RequestMapping("/showAll")
//    public String showAllUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "/user/show-all-users";
//    }
//
//    @RequestMapping("/delete")
//    public String deleteUser(@RequestParam Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        userOptional.ifPresent(userRepository::delete);
//        return "redirect:/user/showAll";
//    }
}
