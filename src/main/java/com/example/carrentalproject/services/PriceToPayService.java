package com.example.carrentalproject.services;

import com.example.carrentalproject.model.Rent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PriceToPayService {

    public double priceToPay(Rent rent) {
        double pricePerDay = rent.getCar().getPricePerDay();
        LocalDate start = rent.getStartDate();
        LocalDate end = rent.getReturnDate();
        double days = ChronoUnit.DAYS.between(start, end);
        return pricePerDay * days;
    }
}
