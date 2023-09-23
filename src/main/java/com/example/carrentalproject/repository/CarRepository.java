package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Car;
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

    List<Car> findByRatingAverageGreaterThan(double avg);

}
