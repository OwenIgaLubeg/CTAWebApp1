package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.Cafeterias;
import com.owen.CoffeeTraceabilityApp.repository.CafeteriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafeteriasService {

    @Autowired
    private CafeteriasRepository cafeteriasRepository;

    public List<Cafeterias> getAllCafeterias() {
        return cafeteriasRepository.findAll();
    }

    public Optional<Cafeterias> getCafeteriaById(Long id) {
        return cafeteriasRepository.findById(id);
    }

    public Cafeterias addCafeteria(Cafeterias cafeteria) {
        return cafeteriasRepository.save(cafeteria);
    }

    public Cafeterias updateCafeteria(Long id, Cafeterias cafeteriaDetails) {
        Cafeterias cafeteria = cafeteriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cafeteria not found"));
        cafeteria.setName(cafeteriaDetails.getName());
        cafeteria.setPhoneNumber(cafeteriaDetails.getPhoneNumber());
        cafeteria.setEmail(cafeteriaDetails.getEmail());
        cafeteria.setLocation(cafeteriaDetails.getLocation());
        cafeteria.setServedCoffeeDetails(cafeteriaDetails.getServedCoffeeDetails());
        cafeteria.setUser(cafeteriaDetails.getUser());
        return cafeteriasRepository.save(cafeteria);
    }

    public void deleteCafeteria(Long id) {
        cafeteriasRepository.deleteById(id);
    }

    public void saveCafeteria(Cafeterias cafeteria) {
        cafeteriasRepository.save(cafeteria);
    }
}
