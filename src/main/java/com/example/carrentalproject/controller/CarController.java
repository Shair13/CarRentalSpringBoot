package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
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
    private final OpinionRepository opinionRepository;

    public CarController(CarRepository carRepository, OpinionRepository opinionRepository) {
        this.carRepository = carRepository;
        this.opinionRepository = opinionRepository;
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
        PageRequest pageable = PageRequest.of(page, 10, sort);
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
        List<Integer> allRatings = opinionRepository.findAllByCar(car)
                .stream()
                .map(Opinion::getRating)
                .toList();

        double avgRating = allRatings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0); // Ustawiam 0.0 jeżeli lista jest pusta

        String avgToString = avgRating + "0";
        String edit = avgToString.substring(0, 4);
        double result = Double.parseDouble(edit);

        carRepository.updateAvgRating(result, car.getId());
        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/delete")
    public String deleteCar(@RequestParam Long id){
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(carRepository::delete);
        return "redirect:/admin/cars";
    }

    @RequestMapping("/car/details")
    public String carDetails(@RequestParam Long id, Model model){
        Optional<Car> carOptional = carRepository.findById(id);
        carOptional.ifPresent(c -> model.addAttribute("car", c));
        return "car/car-admin-details";
    }

    @ModelAttribute("types")
    public List<String> types(){
        List<String> types = new ArrayList<>();
        types.add("Sedan");
        types.add("Kombi");
        types.add("SUV");
        types.add("Coupe");
        types.add("Kabriolet");
        types.add("Hatchback");
        types.add("Elektryczny");
        types.add("Hybryda");
        return types;
//        List<TypeOfCar> types = new ArrayList<>();
//        types.add(new TypeOfCar(1,"Sedan"));
//        types.add(new TypeOfCar(2, "Kombi"));
//        types.add(new TypeOfCar(3, "SUV"));
//        types.add(new TypeOfCar(4, "Coupe"));
//        types.add(new TypeOfCar(5, "Kabriolet"));
//        types.add(new TypeOfCar(6, "Hatchback"));
//        types.add(new TypeOfCar(7, "Elektryczny"));
//        types.add(new TypeOfCar(8, "Hybryda"));
//        return types;
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
