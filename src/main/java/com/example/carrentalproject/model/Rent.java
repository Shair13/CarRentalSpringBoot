package com.example.carrentalproject.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public Rent() {
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Department getTakingPlace() {
        return takingPlace;
    }

    public void setTakingPlace(Department takingPlace) {
        this.takingPlace = takingPlace;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Department getReturningPlace() {
        return returningPlace;
    }

    public void setReturningPlace(Department returningPlace) {
        this.returningPlace = returningPlace;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", customer=" + customer +
                ", employee=" + employee +
                ", car=" + car +
                ", startDate=" + startDate +
                ", takingPlace=" + takingPlace +
                ", returnDate=" + returnDate +
                ", returningPlace=" + returningPlace +
                ", price=" + price +
                '}';
    }
}
