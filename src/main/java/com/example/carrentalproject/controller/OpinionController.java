package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.services.CarService;
import com.example.carrentalproject.services.OpinionService;
import com.example.carrentalproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin")
public class OpinionController {

    private final OpinionService opinionService;
    private final UserService userService;
    private final CarService carService;

    @GetMapping("/opinion/add")
    public String displayAddForm(Model model) {
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("rating", opinionService.getRatingList());
        return "opinion/opinion-add-form";
    }

    @PostMapping("/opinion/add")
    public String processAddForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-add-form";
        }
        opinionService.save(opinion);
        return "redirect:/admin/opinions";
    }

    @RequestMapping("/opinions")
    public String showAllOpinions(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("opinions", opinionService.findAll(page));
        return "opinion/opinion-list";
    }


    @RequestMapping("/opinions/bycars")
    public String showOpinionsByCars(Model model) {
        model.addAttribute("carsAvg", carService.findWithRatingGreaterThan(0.0));
        return "opinion/opinion-bycar-list";
    }

    @RequestMapping("/opinions/bycar/details")
    public String showOpinionsByCarDetails(Model model, @RequestParam Long id) {
        model.addAttribute("opinions", opinionService.findAllByCarId(id));
        return "opinion/opinion-bycar-details";
    }

    @GetMapping("/opinion/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        model.addAttribute("opinion", opinionService.findById(id));
        model.addAttribute("rating", opinionService.getRatingList());
        return "opinion/opinion-edit-form";
    }

    @PostMapping("/opinion/edit")
    public String processUpdateForm(@Valid Opinion opinion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "opinion/opinion-edit-form";
        }
        opinionService.save(opinion);

        return "redirect:/admin/opinions";
    }

    @RequestMapping("/opinion/delete")
    public String deleteOpinion(@RequestParam Long id) {
        opinionService.delete(id);
        return "redirect:/admin/opinions";
    }

    @ModelAttribute("users")
    public List<User> getUserList() {
        return userService.findAll();
    }

    @ModelAttribute("cars")
    public List<Car> getCarList() {
        return carService.findAll();
    }
}
