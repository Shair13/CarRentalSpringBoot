package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.services.CurrentUser;
import com.example.carrentalproject.services.RatingService;
import com.example.carrentalproject.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    private final CarRepository carRepository;
    private final OpinionRepository opinionRepository;
    private final RatingService ratingService;
    private final UserService userService;


    public HomeController(CarRepository carRepository, OpinionRepository opinionRepository, RatingService ratingService, UserService userService) {
        this.carRepository = carRepository;
        this.opinionRepository = opinionRepository;
        this.ratingService = ratingService;
        this.userService = userService;
    }

//    @GetMapping("/test")
//    public String admin(@AuthenticationPrincipal CurrentUser customUser, Model model) {
//        if (customUser != null) {
//            User entityUser = customUser.getUser();
//            model.addAttribute("test", entityUser);
//        }
//        return "home/test";
//    }

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
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/fleet")
    public String showAllCars(Model model, @RequestParam(defaultValue = "0") int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 20, sort);
        model.addAttribute("cars", carRepository.findAll(pageable));
        return "home/fleet-list";
    }

    @GetMapping("/opinions")
    public String showCarOpinions(@RequestParam Long carId, Model model, HttpSession session) {
        Optional<Car> carOptional = carRepository.findById(carId);
        Opinion newOpinion = new Opinion();
        carOptional.ifPresent(c -> {
            model.addAttribute("car", c);
            newOpinion.setCar(c);
        });
//        if (session.getAttribute("user") != null) {
//            User user = (User) session.getAttribute("user");
//            newOpinion.setUser(user);
//        }
        model.addAttribute("rating", ratingService.getRatingList());
        model.addAttribute("opinions", opinionRepository.findAllByCar(carOptional.get(), Sort.by(Sort.Order.desc("id"))));
        model.addAttribute("opinion", newOpinion);
        return "home/opinions-to-car";
    }

    @PostMapping("/opinions")
    public String processOpinionToCar(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/opinions?carId=" + opinion.getCar().getId();
        }
        opinionRepository.save(opinion);
        ratingService.ratingAverageRefreshByOpinion(opinion);
        return "redirect:/opinions?carId=" + opinion.getCar().getId();
    }

    @RequestMapping("/contact")
    public String showContactPage(Model model) {
//        model.addAttribute("CEO", userRepository.findAllByType("admin"));
        return "home/contact";

    }

    @RequestMapping("/available")
    public String availableCars(Model model, @RequestParam(defaultValue = "0") int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 20, sort);
        model.addAttribute("available", carRepository.findByStatusContains("available", pageable));
        return "home/available";
    }
}
