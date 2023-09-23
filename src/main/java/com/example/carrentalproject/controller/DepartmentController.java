package com.example.carrentalproject.controller;

import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.repository.DepartmentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/department/add")
    public String displayAddForm(Model model){
        model.addAttribute("department", new Department());
        return "department/department-add-form";
    }

    @PostMapping("/department/add")
    public String processAddForm(@Valid Department department, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "department/department-add-form";
        }
        departmentRepository.save(department);
        return "redirect:/admin/departments";
    }

    @RequestMapping("/departments")
    public String showAllDepartments(Model model, @RequestParam(defaultValue = "0") int page){
        PageRequest pageable = PageRequest.of(page, 10);
        model.addAttribute("departments", departmentRepository.findAll(pageable));
        return "department/department-list";
    }

    @GetMapping("/department/edit")
    public String displayUpdateForm(@RequestParam Long id, Model model){
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        departmentOptional.ifPresent(d -> model.addAttribute("department", d));
        return "/department/department-edit-form";
    }

    @PostMapping("/department/edit")
    public String processUpdateForm(@Valid Department department, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "department/department-edit-form";
        }
        departmentRepository.save(department);
        return "redirect:/admin/departments";
    }

    @RequestMapping("/department/delete")
    public String deleteDepartment(@RequestParam Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        optionalDepartment.ifPresent(departmentRepository::delete);
        return "redirect:/admin/departments";
    }
}
