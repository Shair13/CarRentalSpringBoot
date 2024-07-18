package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.RentNotFoundException;
import com.example.carrentalproject.model.Rent;
import com.example.carrentalproject.repository.RentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Page<Rent> findAll(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 50, sort);
        return rentRepository.findAll(pageable);
    }

    public Rent findById(Long id) {
        return rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public void updateStatus(String status, Rent rent) {
        rentRepository.updateStatus(status, rent.getId());
    }

    public void delete(Long id) {
        rentRepository.deleteRentById(id);
    }
}
