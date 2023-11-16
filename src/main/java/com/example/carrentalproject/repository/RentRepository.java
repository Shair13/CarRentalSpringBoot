package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findAllByCustomerAndStatus(User user, String status);

    List<Rent> findAllByCustomerOrderByIdDesc(User user);

    @Transactional
    @Modifying
    @Query(value = "UPDATE rentals SET status = ?1 WHERE id = ?2",
            nativeQuery = true)
    void updateStatus(String status, Long id);


}
