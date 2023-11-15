package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.DepartmentRepository;
import com.example.carrentalproject.repository.RentRepository;
import com.example.carrentalproject.repository.UserRepository;
import com.example.carrentalproject.services.PriceToPayService;
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
@RequestMapping("/admin")
@SessionAttributes("user")
public class RentController {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final CarRepository carRepository;
    private final PriceToPayService priceToPayService;

    public RentController(RentRepository rentRepository, UserRepository userRepository, DepartmentRepository departmentRepository, CarRepository carRepository, PriceToPayService priceToPayService) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.carRepository = carRepository;
        this.priceToPayService = priceToPayService;
    }

    @GetMapping("/rent/add")
    public String displayAddForm(Model model) {
        model.addAttribute("rent", new Rent());
        return "rent/rent-add-form";
    }

    @PostMapping("/rent/add")
    public String processAddForm(@Valid Rent rent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rent/rent-add-form";
        }
        carRepository.updateCarStatus("reserved", rent.getCar().getId());
        rent.setPrice(priceToPayService.priceToPay(rent));
        rentRepository.save(rent);
        return "redirect:/admin/rentals";
    }

    @GetMapping("/rent/end")
    public String displayEndRentingForm(Model model, @RequestParam Long rentId) {
        Optional<Rent> rentOptional = rentRepository.findById(rentId);
        rentOptional.ifPresent(r -> model.addAttribute("rent", r));
        return "rent/rent-end-form";
    }

    @PostMapping("/rent/end")
    public String processEndRentingForm(@Valid Rent rent, @RequestParam int mileage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rent/rent-end-form";
        }
        if (mileage <= rent.getCar().getMileage()) {
            return "rent/rent-end-form";
        }
        rentRepository.updateStatus("ended", rent.getId());
        carRepository.updateCarStatusAndMileage("available", mileage, rent.getCar().getId());
        return "redirect:/admin/rentals";
    }

    @RequestMapping("/rentals")
    public String showAllRentals(Model model, @RequestParam(defaultValue = "0") int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 50, sort);
        model.addAttribute("rentals", rentRepository.findAll(pageable));
        return "rent/rent-list";
    }

    @GetMapping("/rent/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        rentOptional.ifPresent(r -> model.addAttribute("rent", r));
        return "rent/rent-edit-form";
    }

    @PostMapping("/rent/edit")
    public String processUpdateForm(@Valid Rent rent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rent/rent-edit-form";
        }
        rent.setPrice(priceToPayService.priceToPay(rent));
        rentRepository.save(rent);
        return "redirect:/admin/rentals";
    }

    @RequestMapping("/rent/delete")
    public String deleteRent(@RequestParam Long id) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        rentOptional.ifPresent(rentRepository::delete);
        return "redirect:/admin/rentals";
    }

    @ModelAttribute("employees")
    public List<User> getAllEmployees() {
        return userRepository.findAllByType("employee");
    }

    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userRepository.findAllByType("user");
    }

    @ModelAttribute("departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @ModelAttribute("cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @ModelAttribute("freeCars")
    public List<Car> getAllAvailableCars() {
        return carRepository.findByStatusContains("available");
    }

    @ModelAttribute("rentStatuses")
    public List<String> getRentStatuses() {
        List<String> statuses = new ArrayList<>();
        statuses.add("process");
        statuses.add("ended");
        return statuses;
    }
}
