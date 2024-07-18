package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.services.CarService;
import com.example.carrentalproject.services.OpinionService;
import com.example.carrentalproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@SessionAttributes("user")
public class HomeController {

    private final UserService userService;
    private final CarService carService;
    private final OpinionService opinionService;

    @GetMapping("/")
    public String home() {
        return "home/home";
    }

    @GetMapping(path = "/registration")
    public String displayAddForm(Model model) {
        model.addAttribute("registration", new User());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String processAddForm(@Valid User user, BindingResult bindingResult, @RequestParam(name = "pass") String pass) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(pass)) {
            return "home/registration";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String displayLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return "home/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        return userService.loginUser(email, password, model);
    }

    @GetMapping("/fleet")
    public String showAllCars(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("cars", carService.findAll(page));
        return "home/fleet-list";
    }

    @GetMapping("/opinions")
    public String showCarOpinions(@RequestParam Long carId, Model model, HttpSession session) {
        Car car = carService.findById(carId);
        Opinion newOpinion = opinionService.newOpinion(car, session);

        model.addAttribute("rating", opinionService.getRatingList());
        model.addAttribute("opinions", opinionService.findAllByCarId(carId));
        model.addAttribute("opinion", newOpinion);
        return "home/opinions-to-car";
    }

    @PostMapping("/opinions")
    public String processOpinionToCar(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/opinions?carId=" + opinion.getCar().getId();
        }
        opinionService.save(opinion);
        return "redirect:/opinions?carId=" + opinion.getCar().getId();
    }

    @RequestMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("CEO", userService.findAllByType("admin"));
        return "home/contact";

    }

    @RequestMapping("/available")
    public String availableCars(Model model, @RequestParam(defaultValue = "0") int page) {

        model.addAttribute("available", carService.findByStatus("available", page));
        return "home/available";
    }
}
