package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.TypeOfCar;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.TypeOfCarRepository;
import com.example.carrentalproject.services.RatingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class CarController {

    private final CarRepository carRepository;
    private final RatingService ratingService;
    private final TypeOfCarRepository typeOfCarRepository;

    public CarController(CarRepository carRepository, RatingService ratingService, TypeOfCarRepository typeOfCarRepository) {
        this.carRepository = carRepository;
        this.ratingService = ratingService;
        this.typeOfCarRepository = typeOfCarRepository;
    }

    @GetMapping("/car/add")
    public String displayAddForm(Model model){
        model.addAttribute("car", new Car());
        return "car/car-add-form";
    }

    @PostMapping("/car/add")
    public String processAddForm(@Valid Car car, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "car/car-add-form";
        }
        carRepository.save(car);
        return "redirect:/admin/cars";
    }

    @RequestMapping("/cars")
    public String showAllCars(Model model, @RequestParam(defaultValue = "0") int page){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 5, sort);
        model.addAttribute("cars", carRepository.findAll(pageable));
        return "car/car-list";
    }


    @GetMapping("/car/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model){
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(car -> model.addAttribute("car", car));
        return "car/car-edit-form";
    }

    @PostMapping("/car/edit")
    public String processUpdateForm(@Valid Car car, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "car/car-edit-form";
        }
        carRepository.save(car);
        ratingService.ratingAverageRefreshByCar(car);
        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/delete")
    public String deleteCar(@RequestParam Long id){
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(c -> {
            carRepository.delete(c);
            ratingService.ratingAverageRefreshByCar(c);
        });
        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/details")
    public String carDetails(@RequestParam Long id, Model model){
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(c -> model.addAttribute("car", c));
        return "car/car-admin-details";
    }

    @ModelAttribute("types")
    public List<TypeOfCar> types(){
        return typeOfCarRepository.findAll();
    }

    @ModelAttribute("statuses")
    public List<String> statuses(){
        List<String> statuses = new ArrayList<>();
        statuses.add("service");
        statuses.add("reserved");
        statuses.add("available");
        return statuses;
    }
}
