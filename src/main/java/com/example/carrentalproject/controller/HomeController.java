package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.services.RatingService;
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
@SessionAttributes("user")
public class HomeController {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final OpinionRepository opinionRepository;
    private final RatingService ratingService;

    public HomeController(UserRepository userRepository, CarRepository carRepository, OpinionRepository opinionRepository, RatingService ratingService) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.opinionRepository = opinionRepository;
        this.ratingService = ratingService;
    }

    @RequestMapping("/")
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
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String displayLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
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
//        if (typeOfUser.equals("employee") && user.getPassword().equals(password)) {
//            return "";
//        }
        if ("admin".equals(typeOfUser) && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/admin/dashboard";
        }
        return "redirect:/login";
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
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            newOpinion.setUser(user);
        }

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
    public String showContactPage(Model model){
        model.addAttribute("CEO", userRepository.findAllByType("admin"));
        return "home/contact";

    }

    @RequestMapping("/available")
    public String availableCars(Model model, @RequestParam(defaultValue = "0") int page){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 20, sort);
        model.addAttribute("available", carRepository.findByStatusContains("available", pageable));
        return "home/available";
    }

    // ***

    @ModelAttribute("rating")
    public List<Integer> getRatingArr() {
        List<Integer> rating = new ArrayList<>();
        rating.add(1);
        rating.add(2);
        rating.add(3);
        rating.add(4);
        rating.add(5);
        return rating;
    }
}
