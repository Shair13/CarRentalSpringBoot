package com.example.carrentalproject.repository;

import com.example.carrentalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET first_name = ?1, last_name = ?2, email = ?3 WHERE id = ?4",
            nativeQuery = true)
    void update(String firstName, String lastName, String email, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET password = ?1 WHERE id = ?2",
            nativeQuery = true)
    void updatePassword(String password, Long id);

   List<User> findAllByType(String status);

   Optional<User> findByEmail(String email);

}


