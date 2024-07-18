package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.DepartmentNotFoundException;
import com.example.carrentalproject.model.Department;
import com.example.carrentalproject.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Page<Department> findAll(int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return departmentRepository.findAll(pageable);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
    }

    public void delete(Long id) {
        departmentRepository.deleteDepartmentById(id);
    }
}
