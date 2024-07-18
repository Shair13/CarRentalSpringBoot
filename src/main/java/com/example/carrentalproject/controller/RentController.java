package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.services.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class RentController {

    private final RentService rentService;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final CarService carService;
    private final PriceToPayService priceToPayService;

    public RentController(RentService rentService, UserService userService, DepartmentService departmentService, CarService carService, PriceToPayService priceToPayService) {
        this.rentService = rentService;
        this.userService = userService;
        this.departmentService = departmentService;
        this.carService = carService;
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
        carService.updateCarStatus("reserved", rent);
        rent.setPrice(priceToPayService.priceToPay(rent));
        rentService.save(rent);
        return "redirect:/admin/rentals";
    }

    @GetMapping("/rent/end")
    public String displayEndRentingForm(Model model, @RequestParam Long id) {
        model.addAttribute("rent", rentService.findById(id));
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
        rentService.updateStatus("ended", rent);
        carService.updateStatusAndMileage("available", mileage, rent);
        return "redirect:/admin/rentals";
    }

    @RequestMapping("/rentals")
    public String showAllRentals(Model model, @RequestParam(defaultValue = "0") int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 50, sort);
        model.addAttribute("rentals", rentService.findAll(page));
        return "rent/rent-list";
    }

    @GetMapping("/rent/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        model.addAttribute("rent", rentService.findById(id));
        return "rent/rent-edit-form";
    }

    @PostMapping("/rent/edit")
    public String processUpdateForm(@Valid Rent rent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rent/rent-edit-form";
        }
        rent.setPrice(priceToPayService.priceToPay(rent));
        rentService.save(rent);
        return "redirect:/admin/rentals";
    }

    @RequestMapping("/rent/delete")
    public String deleteRent(@RequestParam Long id) {
        rentService.delete(id);
        return "redirect:/admin/rentals";
    }

    @ModelAttribute("employees")
    public List<User> getAllEmployees() {
        return userService.findAllByType("employee");
    }

    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userService.findAllByType("user");
    }

    @ModelAttribute("departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @ModelAttribute("cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @ModelAttribute("freeCars")
    public List<Car> getAllAvailableCars() {
        return carService.findByStatus("available");
    }

    @ModelAttribute("rentStatuses")
    public List<String> getRentStatuses() {
        List<String> statuses = new ArrayList<>();
        statuses.add("process");
        statuses.add("ended");
        return statuses;
    }
}
