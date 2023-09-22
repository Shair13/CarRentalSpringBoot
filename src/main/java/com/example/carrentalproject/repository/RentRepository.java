package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findLast5ByCustomer(User user);

    List<Rent> findAllByCustomerOrderByIdDesc(User user);

}
