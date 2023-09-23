//package com.example.carrentalproject.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import project.model.Car;
//import project.model.Department;
//import project.model.Rent;
//import project.model.User;
//import project.repository.CarRepository;
//import project.repository.DepartmentRepository;
//import project.repository.RentRepository;
//import project.repository.UserRepository;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("rent")
//@SessionAttributes("user")
//public class RentController {
//
//    private final RentRepository rentRepository;
//    private final UserRepository userRepository;
//    private final DepartmentRepository departmentRepository;
//    private final CarRepository carRepository;
//
//    public RentController(RentRepository rentRepository, UserRepository userRepository, DepartmentRepository departmentRepository, CarRepository carRepository) {
//        this.rentRepository = rentRepository;
//        this.userRepository = userRepository;
//        this.departmentRepository = departmentRepository;
//        this.carRepository = carRepository;
//    }
//
//    // Crud dla użytkownika
//
//    @GetMapping("/udashboard")
//    public String displayUserAddForm(Model model) {
//        model.addAttribute("rent", new Rent());
//        return "rent/user-dashboard-add-rent";
//    }
//
//    @PostMapping("/udashboard")
//    public String processUserAddForm(@Valid Rent rent, HttpSession session, BindingResult bindingResult) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
//        if (bindingResult.hasErrors()) {
//            return "redirect:/rent/udashboard";
//        }
//        User user = (User) session.getAttribute("user");
//        rent.setCustomer(user);
//        rent.setPrice(priceToPay(rent));
//        rentRepository.save(rent);
//        return "redirect:/udashboard";
//    }
//
//    @RequestMapping("/udashboard/all")
//    public String showAllUserRentals(Model model, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            return "redirect:/login";
//        }
//        User user = (User) session.getAttribute("user");
//        model.addAttribute("rentals", rentRepository.findAllByCustomerOrderByIdDesc(user));
//        return "rent/user-dashboard-all-rentals";
//    }
//
//
//    // CRUD ogólnego dostępu
//
//    @GetMapping("/add")
//    public String displayAddForm(Model model) {
//        model.addAttribute("rent", new Rent());
//        return "/rent/add-form";
//    }
//
//    @PostMapping("/add")
//    public String processAddForm(@Valid Rent rent, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/rent/add";
//        }
//        rent.setPrice(priceToPay(rent));
//        rentRepository.save(rent);
//        return "redirect:/rent/showAll";
//    }
//
//    @RequestMapping("/showAll")
//    public String showAllRentals(Model model) {
//        model.addAttribute("rentals", rentRepository.findAll());
//        return "rent/show-all-rentals";
//    }
//
//    @GetMapping("/update")
//    public String displayUpdateForm(@RequestParam Long id, Model model) {
//        Optional<Rent> rentOptional = rentRepository.findById(id);
//        rentOptional.ifPresent(r -> model.addAttribute("rent", r));
//        return "rent/update-form";
//    }
//
//    @PostMapping("/update")
//    public String processUpdateForm(@Valid Rent rent, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/rent/update";
//        }
//        rent.setPrice(priceToPay(rent));
//        rentRepository.save(rent);
//        return "redirect:/rent/showAll";
//    }
//
//    @RequestMapping("/delete")
//    public String deleteRent(@RequestParam Long id) {
//        Optional<Rent> rentOptional = rentRepository.findById(id);
//        rentOptional.ifPresent(rentRepository::delete);
//        return "redirect:/rent/showAll";
//    }
//
//    public double priceToPay(Rent rent) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        double pricePerDay = rent.getCar().getPricePerDay();
//        LocalDate start = LocalDate.parse(rent.getStartDate(), formatter);
//        LocalDate end = LocalDate.parse(rent.getReturnDate(), formatter);
//        double days = ChronoUnit.DAYS.between(start, end);
//        return pricePerDay * days;
//    }
//
//    @ModelAttribute("employees")
//    public List<User> getAllEmployees() {
//        return userRepository.findAllByType("employee");
//    }
//
//    @ModelAttribute("users")
//    public List<User> getAllUsers() {
//        return userRepository.findAllByType("user");
//    }
//
//    @ModelAttribute("departments")
//    public List<Department> getAllDepartments() {
//        return departmentRepository.findAll();
//    }
//
//    @ModelAttribute("cars")
//    public List<Car> getAllCars() {
//        return carRepository.findAll();
//    }
//
//}
