package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.TypeOfCar;
import com.example.carrentalproject.services.CarService;
import com.example.carrentalproject.services.RatingService;
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
@RequestMapping("admin")
public class CarController {

    private final CarService carService;
    private final RatingService ratingService;

    @GetMapping("/car/add")
    public String displayAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "car/car-add-form";
    }

    @PostMapping("/car/add")
    public String processAddForm(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "car/car-add-form";
        }
        carService.save(car);
        return "redirect:/admin/cars";
    }

    @GetMapping("/cars")
    public String showAllCars(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("cars", carService.findAll(page));
        return "car/car-list";
    }


    @GetMapping("/car/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "car/car-edit-form";
    }

    @PostMapping("/car/edit")
    public String processUpdateForm(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "car/car-edit-form";
        }
        carService.save(car);
        ratingService.ratingAverageRefreshByCar(car.getId());
        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/delete")
    public String deleteCar(@RequestParam Long id) {
        carService.delete(id);
        ratingService.ratingAverageRefreshByCar(id);

        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/details")
    public String carDetails(@RequestParam Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "car/car-admin-details";
    }

    @ModelAttribute("types")
    public List<TypeOfCar> types() {
        return carService.findAllTypesOfCar();
    }

    @ModelAttribute("statuses")
    public List<String> statuses() {
        List<String> statuses = new ArrayList<>();
        statuses.add("service");
        statuses.add("reserved");
        statuses.add("available");
        return statuses;
    }
}
