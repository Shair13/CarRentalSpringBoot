package com.example.carrentalproject.services;

import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.repository.CarRepository;
import com.example.carrentalproject.repository.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

    private final OpinionRepository opinionRepository;
    private final CarRepository carRepository;

    public RatingService(OpinionRepository opinionRepository, CarRepository carRepository) {
        this.opinionRepository = opinionRepository;
        this.carRepository = carRepository;
    }



    public void ratingAverageRefreshByOpinion(Opinion opinion) {
        List<Integer> allRatings = opinionRepository.findAllByCar(opinion.getCar())
                .stream()
                .map(Opinion::getRating)
                .toList();

        double avgRating = allRatings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0); // Ustawiam 0.0 jeżeli lista jest pusta

        String avgToString = avgRating + "0";
        String edit = avgToString.substring(0, 4);
        double result = Double.parseDouble(edit);

        carRepository.updateAvgRating(result, opinion.getCar().getId());
    }

    public void ratingAverageRefreshByCar(Car car) {
        List<Integer> allRatings = opinionRepository.findAllByCar(car)
                .stream()
                .map(Opinion::getRating)
                .toList();

        double avgRating = allRatings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0); // Ustawiam 0.0 jeżeli lista jest pusta

        String avgToString = avgRating + "0";
        String edit = avgToString.substring(0, 4);
        double result = Double.parseDouble(edit);

        carRepository.updateAvgRating(result, car.getId());
    }

    public List<Integer> getRatingList() {
        List<Integer> rating = new ArrayList<>();
        rating.add(1);
        rating.add(2);
        rating.add(3);
        rating.add(4);
        rating.add(5);
        return rating;
    }
}
