package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.CarNotFoundException;
import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.model.TypeOfCar;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.TypeOfCarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final TypeOfCarRepository typeOfCarRepository;

    public CarService(CarRepository carRepository, TypeOfCarRepository typeOfCarRepository) {
        this.carRepository = carRepository;
        this.typeOfCarRepository = typeOfCarRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Page<Car> findAll(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 5, sort);
        return carRepository.findAll(pageable);
    }

    public List<Car> findByStatus(String status) {
        return carRepository.findByStatusContains(status);
    }
    public List<Car> findByStatus(String status, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 20, sort);
        return carRepository.findByStatusContains(status, pageable);
    }


    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public void updateCarStatus(String status, Rent rent) {
        carRepository.updateCarStatus(status, rent.getCar().getId());
    }

    public void delete(Long id) {
        carRepository.deleteCarById(id);
    }

    public List<TypeOfCar> findAllTypesOfCar() {
        return typeOfCarRepository.findAll();
    }

    public void updateStatusAndMileage(String status, int mileage, Rent rent){
        carRepository.updateCarStatusAndMileage(status, mileage, rent.getCar().getId());
    }
}
