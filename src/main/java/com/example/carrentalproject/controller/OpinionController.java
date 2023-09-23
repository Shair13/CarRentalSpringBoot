package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class OpinionController {

    private final OpinionRepository opinionRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public OpinionController(OpinionRepository opinionRepository, UserRepository userRepository, CarRepository carRepository) {
        this.opinionRepository = opinionRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/opinion/add")
    public String displayAddForm(Model model) {
        model.addAttribute("opinion", new Opinion());
        return "opinion/opinion-add-form";
    }

    @PostMapping("/opinion/add")
    public String processAddForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-add-form";
        }
        List<Integer> allRatings = opinionRepository.findAllByCar(opinion.getCar())
                .stream()
                .map(Opinion::getRating)
                .collect(Collectors.toList());

        allRatings.add(opinion.getRating());

        double avgRating = allRatings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0); // Ustawiam 0.0 jeżeli lista jest pusta

        carRepository.updateAvgRating(avgRating, opinion.getCar().getId());
        opinionRepository.save(opinion);
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
    public String showOpinionsByCarDetails(Model model,@RequestParam Long id) {
        Car car = carRepository.findById(id).get();
        model.addAttribute("opinions", opinionRepository.findAllByCar(car));
        return "opinion/opinion-bycar-details";
    }

    @GetMapping("/opinion/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
        opinionOptional.ifPresent(o -> model.addAttribute("opinion", o));
        return "opinion/opinion-edit-form";
    }

    @PostMapping("/opinion/edit")
    public String processUpdateForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-edit-form";
        }
        opinionRepository.save(opinion);
        return "redirect:/admin/opinions";
    }

    @RequestMapping("/opinion/delete")
    public String deleteOpinion(@RequestParam Long id) {
        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
        opinionOptional.ifPresent(opinionRepository::delete);
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
