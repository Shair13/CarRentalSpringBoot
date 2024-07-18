package com.example.carrentalproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "opinions")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private User user;
    @NotNull
    @ManyToOne
    private Car car;

    @Column(length = 2000)
    @Size(max = 2000)
    private String content;

    @Column(length = 1)
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    private String created;
    private String updated = null;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}