package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    List<Opinion> findAllByCar(Car car);

    List<Opinion> findAllByCar(Car car, Sort sortByIdDesc);

    void deleteOpinionById(Long id);
}
