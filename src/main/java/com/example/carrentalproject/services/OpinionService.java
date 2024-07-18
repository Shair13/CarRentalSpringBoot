package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.OpinionNotFoundException;
import com.example.carrentalproject.model.Car;
import com.example.carrentalproject.model.Opinion;
import com.example.carrentalproject.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpinionService {

    private final OpinionRepository opinionRepository;

    public Opinion save(Opinion opinion) {
        return opinionRepository.save(opinion);
    }

    public List<Opinion> findAll() {
        return opinionRepository.findAll();
    }

    public Page<Opinion> findAll(int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return opinionRepository.findAll(pageable);
    }

    public List<Opinion> findAllByCar(Car car) {
        return opinionRepository.findAllByCar(car, Sort.by(Sort.Order.desc("id")));
    }

    public Opinion findById(Long id) {
        return opinionRepository.findById(id).orElseThrow(OpinionNotFoundException::new);
    }

    public void delete(Long id) {
        opinionRepository.deleteOpinionById(id);
    }
}
