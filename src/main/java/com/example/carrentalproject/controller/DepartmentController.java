package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/department/add")
    public String displayAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/department-add-form";
    }

    @PostMapping("/department/add")
    public String processAddForm(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/department-add-form";
        }
        departmentService.save(department);
        return "redirect:/admin/departments";
    }

    @GetMapping("/departments")
    public String showAllDepartments(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("departments", departmentService.findAll(page));
        return "department/department-list";
    }

    @GetMapping("/department/details")
    public String carDetails(@RequestParam Long id, Model model) {
        model.addAttribute("dep", departmentService.findById(id));
        return "department/department-admin-details";
    }

    @GetMapping("/department/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model) {
        model.addAttribute("department", departmentService.findById(id));
        return "/department/department-edit-form";
    }

    @PostMapping("/department/edit")
    public String processUpdateForm(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/department-edit-form";
        }
        departmentService.save(department);
        return "redirect:/admin/departments";
    }

    @DeleteMapping("/department/delete")
    public String deleteDepartment(@RequestParam Long id) {
        departmentService.delete(id);
        return "redirect:/admin/departments";
    }
}
