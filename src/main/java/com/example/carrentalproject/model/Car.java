package com.example.carrentalproject.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    private String status = "servis";
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

    public Car() {
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public String getFullName() {
        return brand + " " + model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }

    public LocalDate getNextInsurance() {
        return nextInsurance;
    }

    public void setNextInsurance(LocalDate nextInsurance) {
        this.nextInsurance = nextInsurance;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", productionDate=" + productionDate +
                ", plates='" + plates + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", status='" + status + '\'' +
                ", mileage=" + mileage +
                ", nextInspection=" + nextInspection +
                ", nextInsurance=" + nextInsurance +
                ", capacity='" + capacity + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
