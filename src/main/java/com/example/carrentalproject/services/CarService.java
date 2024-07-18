package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.CarNotFoundException;
import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.TypeOfCar;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.TypeOfCarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final TypeOfCarRepository typeOfCarRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Page<Car> findAll(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 5, sort);
        return carRepository.findAll(pageable);
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public void delete(Long id) {
        carRepository.deleteCarById(id);
    }

    public List<TypeOfCar> findAllTypesOfCar() {
        return typeOfCarRepository.findAll();
    }
}
