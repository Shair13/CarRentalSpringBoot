//package com.example.carrentalproject.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import project.model.Department;
//import project.repository.DepartmentRepository;
//
//import javax.validation.Valid;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/dep/")
//public class DepartmentController {
//
//    private final DepartmentRepository departmentRepository;
//
//    public DepartmentController(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }
//
//    @GetMapping("/add")
//    public String displayAddForm(Model model){
//        model.addAttribute("department", new Department());
//        return "department/add-form";
//    }
//
//    @PostMapping("/add")
//    public String processAddForm(@Valid Department department, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "redirect:/dep/add";
//        }
//        departmentRepository.save(department);
//        return "redirect:/dep/showAll";
//    }
//
//    @RequestMapping("/showAll")
//    public String showAllDepartments(Model model){
//        model.addAttribute("departments", departmentRepository.findAll());
//        return "department/show-all-departments";
//    }
//
//    @GetMapping("/update")
//    public String displayUpdateForm(@RequestParam Long id, Model model){
//        Optional<Department> departmentOptional = departmentRepository.findById(id);
//        departmentOptional.ifPresent(d -> model.addAttribute("department", d));
//        return "/department/update-form";
//    }
//
//    @PostMapping("/update")
//    public String processUpdateForm(@Valid Department department, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return "redirect:/dep/update";
//        }
//        departmentRepository.save(department);
//        return "redirect:/dep/showAll";
//    }
//
//    @RequestMapping("/delete")
//    public String deleteDepartment(@RequestParam Long id){
//        Optional<Department> optionalDepartment = departmentRepository.findById(id);
//        optionalDepartment.ifPresent(departmentRepository::delete);
//        return "redirect:/dep/showAll";
//    }
//}
