package com.example.carrentalproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class TypeOfCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 30)
    private String type;

    public TypeOfCar() {
    }

    public TypeOfCar(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
