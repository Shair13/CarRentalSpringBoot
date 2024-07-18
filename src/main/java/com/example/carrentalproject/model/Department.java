package com.example.carrentalproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //dawać longa, żeby się nie myliło a w bazie Tiny?
    @Column(length = 50)
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    @Column(length = 10)
    @Size(max = 10)
    private String zipCode;
    @Column(length = 50)
    @Size(max = 50)
    private String phone;

    public String getFullName() {
        return "Oddział " + city + " " + street;
    }
}
