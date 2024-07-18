package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.CarNotFoundException;
import com.example.carrentalproject.exception.OpinionNotFoundException;
import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final CarService carService;

    public void save(Opinion opinion) {
        opinionRepository.save(opinion);
        ratingAverageRefreshByCar(opinion.getCar().getId());
    }

    public List<Opinion> findAll() {
        return opinionRepository.findAll();
    }

    public Page<Opinion> findAll(int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return opinionRepository.findAll(pageable);
    }

    public List<Opinion> findAllByCarId(Long id) {
        Car car = carService.findById(id);
        return opinionRepository.findAllByCar(car, Sort.by(Sort.Order.desc("id")));
    }

    public Opinion findById(Long id) {
        return opinionRepository.findById(id).orElseThrow(OpinionNotFoundException::new);
    }

    public Opinion newOpinion(Car car, HttpSession session) {
        Opinion newOpinion = new Opinion();
        newOpinion.setCar(car);

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            newOpinion.setUser(user);
        }
        return newOpinion;
    }

    public void delete(Long id) {
        Opinion opinion = opinionRepository.findById(id).orElseThrow(CarNotFoundException::new);
        opinionRepository.deleteOpinionById(id);
        ratingAverageRefreshByCar(opinion.getCar().getId());
    }

    public void ratingAverageRefreshByCar(Long id) {
        List<Integer> allRatings = findAllByCarId(id)
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

        carService.updateRating(result, id);
    }

    public List<Integer> getRatingList() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    }
}
