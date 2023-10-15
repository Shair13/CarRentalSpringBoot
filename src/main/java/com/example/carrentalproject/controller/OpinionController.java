package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.services.CurrentUser;
import com.example.carrentalproject.services.RatingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class OpinionController {

    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RatingService ratingService;

    public OpinionController(OpinionRepository opinionRepository, UserRepository userRepository, CarRepository carRepository, RatingService ratingService) {
        this.opinionRepository = opinionRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.ratingService = ratingService;
    }


    @GetMapping("/opinion/add")
    public String displayAddForm(Model model) {
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("rating", ratingService.getRatingList());
        return "opinion/opinion-add-form";
    }

    @PostMapping("/opinion/add")
    public String processAddForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-add-form";
        }
        opinionRepository.save(opinion);
        ratingService.ratingAverageRefreshByOpinion(opinion);
        return "redirect:/admin/opinions";
    }

    @RequestMapping("/opinions")
    public String showAllOpinions(Model model, @RequestParam(defaultValue = "0") int page) {
        PageRequest pageable = PageRequest.of(page, 50);

        model.addAttribute("opinions", opinionRepository.findAll(pageable));
        return "opinion/opinion-list";
    }


    @RequestMapping("/opinions/bycars")
    public String showOpinionsByCars(Model model) {
        model.addAttribute("carsAvg", carRepository.findByRatingAverageGreaterThan(0.0));
        return "opinion/opinion-bycar-list";
    }

    @RequestMapping("/opinions/bycar/details")
    public String showOpinionsByCarDetails(@AuthenticationPrincipal CurrentUser customUser, Model model, @RequestParam Long id) {
        User entityUser = customUser.getUser();
        model.addAttribute("user", entityUser.getUsername());
        Car car = carRepository.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("opinions", opinionRepository.findAllByCar(car));
        return "opinion/opinion-bycar-details";
    }

    @GetMapping("/opinion/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
        opinionOptional.ifPresent(o -> model.addAttribute("opinion", o));
        model.addAttribute("rating", ratingService.getRatingList());
        return "opinion/opinion-edit-form";
    }

    @PostMapping("/opinion/edit")
    public String processUpdateForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-edit-form";
        }
        opinionRepository.save(opinion);
        ratingService.ratingAverageRefreshByOpinion(opinion);
        return "redirect:/admin/opinions";
    }

    @RequestMapping("/opinion/delete")
    public String deleteOpinion(@RequestParam Long id) {
        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
        opinionOptional.ifPresent(o -> {
            opinionRepository.delete(o);
            ratingService.ratingAverageRefreshByOpinion(o);
        });
        return "redirect:/admin/opinions";
    }

    @ModelAttribute("users")
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @ModelAttribute("cars")
    public List<Car> getCarList() {
        return carRepository.findAll();
    }
}
