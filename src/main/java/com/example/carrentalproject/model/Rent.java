package com.example.carrentalproject.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "rentals")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User customer;
    @NotNull
    @ManyToOne
    private User employee;
    @NotNull
    @ManyToOne
    private Car car;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @ManyToOne
    private Department takingPlace;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @NotNull
    @ManyToOne
    private Department returningPlace;
    @Min(value = 0)
    private double price;
    private String status = "process";
}
