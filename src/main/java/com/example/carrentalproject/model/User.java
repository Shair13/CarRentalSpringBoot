package com.example.carrentalproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80)
    @Size(max = 80)
    @NotBlank
    private String firstName;
    @Column(length = 80)
    @Size(max = 80)
    @NotBlank
    private String lastName;
    @NotBlank
    private String type = "user";
    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 4, max = 32)
    private String password;

    public String getFullName() {
        return id + " : " + firstName + " " + lastName;
    }
}
