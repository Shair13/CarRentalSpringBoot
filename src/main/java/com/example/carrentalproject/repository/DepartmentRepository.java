package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    void deleteDepartmentById(Long id);

}
