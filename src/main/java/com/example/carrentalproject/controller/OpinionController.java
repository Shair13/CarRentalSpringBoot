//package com.example.carrentalproject.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import project.model.Car;
//import project.model.Opinion;
//import project.model.User;
//import project.repository.CarRepository;
//import project.repository.OpinionRepository;
//import project.repository.UserRepository;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("opinion")
//public class OpinionController {
//
//    private final OpinionRepository opinionRepository;
//    private final UserRepository userRepository;
//    private final CarRepository carRepository;
//
//    public OpinionController(OpinionRepository opinionRepository, UserRepository userRepository, CarRepository carRepository) {
//        this.opinionRepository = opinionRepository;
//        this.userRepository = userRepository;
//        this.carRepository = carRepository;
//    }
//
//    @GetMapping("/add")
//    public String displayAddForm(Model model){
//        model.addAttribute("opinion", new Opinion());
//        return "opinion/add-form";
//    }
//
//    @PostMapping("/add")
//    public String processAddForm(@Valid Opinion opinion, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return "redirect:/opinion/add";
//        }
//        opinionRepository.save(opinion);
//        return "redirect:/opinion/showAll";
//    }
//
//    @RequestMapping("/showAll")
//    public String showAllOpinions(Model model){
//        model.addAttribute("opinions", opinionRepository.findAll());
//        return "opinion/show-all-opinions";
//    }
//
//    @GetMapping("/update")
//    public String displayUpdateForm(@RequestParam Long id, Model model){
//        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
//        opinionOptional.ifPresent(o -> model.addAttribute("opinion", o));
//        return "opinion/update-form";
//    }
//
//    @PostMapping("/update")
//    public String processUpdateForm(@Valid Opinion opinion, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return "redirect:/opinion/update";
//        }
//        opinionRepository.save(opinion);
//        return "redirect:/opinion/showAll";
//    }
//
//    @RequestMapping("/delete")
//    public String deleteOpinion(@RequestParam Long id){
//        Optional<Opinion> opinionOptional = opinionRepository.findById(id);
//        opinionOptional.ifPresent(opinionRepository::delete);
//        return "redirect:/opinion/showAll";
//    }
//
//    @ModelAttribute("users")
//    public List<User> getUserList(){
//        return userRepository.findAll();
//    }
//
//    @ModelAttribute("cars")
//    public List<Car> getCarList(){
//        return carRepository.findAll();
//    }
//
//    @ModelAttribute("rating")
//    public List<Integer> getRatingArr(){
//        List<Integer> rating = new ArrayList<>();
//        rating.add(1);
//        rating.add(2);
//        rating.add(3);
//        rating.add(4);
//        rating.add(5);
//        return rating;
//    }
//}
