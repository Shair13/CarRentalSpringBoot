package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.DepartmentRepository;
import com.example.carrentalproject.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserDashboardController {

    private final UserService userService;
    private final RentService rentService;
    private final CarService carService;
    private final DepartmentService departmentService;
    private final PriceToPayService priceToPayService;

    @RequestMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("active", rentService.findAllByCustomerAndStatus(user, "process"));
        model.addAttribute("user", user);

        return "acc-user/dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("edit/data")
    public String displayUpdateUserForm(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", userService.findById(user.getId()));
        return "acc-user/edit-data";
    }

    @PostMapping("/edit/data")
    public String processUpdateUserForm(@Valid User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/user/edit/data";
        }
        userService.update(user);
        session.setAttribute("user", user);
        return "redirect:/user/dashboard";
    }

    @GetMapping("edit/password")
    public String displayUpdateUserPassForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "acc-user/edit-password";
    }

    @PostMapping("/edit/password")
    public String processUpdateUserPassForm(@RequestParam String pass1,
                                            @RequestParam String pass2,
                                            HttpSession session) {
        if ("".equals(pass1) || !pass1.equals(pass2)) {
            return "redirect:/user/edit/password";
        }
        User user = (User) session.getAttribute("user");
        userService.updatePassword(pass1, user.getId());
        session.setAttribute("user", user);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/rent")
    public String displayUserAddForm(Model model, HttpSession session) {
        Rent rent = new Rent();
        User bot = userService.findById(15L); //bot jest pod ID 15
        rent.setEmployee(bot);
        model.addAttribute("rent", rent);
        return "acc-user/rent-car";
    }

    @PostMapping("/rent")
    public String processUserAddForm(@Valid Rent rent, HttpSession session, BindingResult bindingResult) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "acc-user/rent-car";
        }
        User user = (User) session.getAttribute("user");
        rent.setCustomer(user);
        rent.setPrice(priceToPayService.priceToPay(rent));
        carService.updateCarStatus("reserved", rent);
        rentService.save(rent);
        return "redirect:/user/rentals";
    }


    @RequestMapping("/rentals")
    public String showAllUserRentals(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("rentals", rentService.findAllByCustomer(user));
        return "acc-user/rent-list";
    }

    @ModelAttribute("freeCars")
    public List<Car> getAllAvailableCars() {
        return carService.findByStatus("available");
    }

    @ModelAttribute("departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

}
