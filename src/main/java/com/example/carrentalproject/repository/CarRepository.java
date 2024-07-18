package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE cars SET rating_average = ?1 WHERE id = ?2",
            nativeQuery = true)
    void updateAvgRating(double avgRating, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cars SET status = ?1 WHERE id = ?2",
            nativeQuery = true)
    void updateCarStatus(String status, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cars SET status = ?1, mileage = ?2 WHERE id = ?3",
            nativeQuery = true)
    void updateCarStatusAndMileage(String status, int mileage, Long id);

    List<Car> findByRatingAverageGreaterThan(double avg);
    List<Car> findByStatusContains(String status);
    List<Car> findByStatusContains(String status, PageRequest request);

    void deleteCarById(Long id);

}
