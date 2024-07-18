package com.example.carrentalproject.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 32)
    @Column(length = 32)
    private String brand;
    @NotBlank
    @Size(max = 32)
    @Column(length = 32)
    private String model;
    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productionDate;
    @NotBlank
    @Size(max = 15)
    @Column(length = 15)
    private String plates;
    @Min(0)
    private double pricePerDay;
    @NotBlank
    private String status = "service";
    @Min(0)
    private int mileage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextInspection;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextInsurance;

    @Column(length = 5)
    @Min(0)
    private double capacity;
    @NotBlank
    @Size(min = 17, max = 17)
    @Column(length = 17)
    private String vin;
    private double ratingAverage;

    public String getFullName() {
        return brand + " " + model;
    }
}
