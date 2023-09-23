package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.DepartmentRepository;
import com.example.carrentalproject.repository.RentRepository;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("rent")
@SessionAttributes("user")
public class RentController {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final CarRepository carRepository;

    public RentController(RentRepository rentRepository, UserRepository userRepository, DepartmentRepository departmentRepository, CarRepository carRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.carRepository = carRepository;
    }

    // Crud dla użytkownika

    @GetMapping("/udashboard")
    public String displayUserAddForm(Model model) {
        model.addAttribute("rent", new Rent());
        return "";
    }

    @PostMapping("/udashboard")
    public String processUserAddForm(@Valid Rent rent, HttpSession session, BindingResult bindingResult) {
        if (session.getAttribute("user") == null) {
            return "";
        }
        if (bindingResult.hasErrors()) {
            return "";
        }
        User user = (User) session.getAttribute("user");
        rent.setCustomer(user);
        rent.setPrice(priceToPay(rent));
        rentRepository.save(rent);
        return "";
    }

    @RequestMapping("/udashboard/all")
    public String showAllUserRentals(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("rentals", rentRepository.findAllByCustomerOrderByIdDesc(user));
        return "";
    }


    // CRUD ogólnego dostępu

    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("rent", new Rent());
        return "";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Rent rent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/rent/add";
        }
        rent.setPrice(priceToPay(rent));
        rentRepository.save(rent);
        return "";
    }

    @RequestMapping("/showAll")
    public String showAllRentals(Model model) {
        model.addAttribute("rentals", rentRepository.findAll());
        return "";
    }

    @GetMapping("/update")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        rentOptional.ifPresent(r -> model.addAttribute("rent", r));
        return "";
    }

    @PostMapping("/update")
    public String processUpdateForm(@Valid Rent rent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "";
        }
        rent.setPrice(priceToPay(rent));
        rentRepository.save(rent);
        return "";
    }

    @RequestMapping("/delete")
    public String deleteRent(@RequestParam Long id) {
        Optional<Rent> rentOptional = rentRepository.findById(id);
        rentOptional.ifPresent(rentRepository::delete);
        return "";
    }

    public double priceToPay(Rent rent) {
        double pricePerDay = rent.getCar().getPricePerDay();
        LocalDate start = rent.getStartDate();
        LocalDate end = rent.getReturnDate();
        double days = ChronoUnit.DAYS.between(start, end);
        return pricePerDay * days;
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

}
