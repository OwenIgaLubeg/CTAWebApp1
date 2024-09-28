package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.CoffeebatchEntity;
import com.owen.CoffeeTraceabilityApp.repository.CoffeebatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeebatchService {

    @Autowired
    private CoffeebatchRepository coffeebatchRepository;

    public List<CoffeebatchEntity> findAll() {
        return coffeebatchRepository.findAll();
    }

    public Optional<CoffeebatchEntity> findById(Long id) {
        return coffeebatchRepository.findById(id); // Return Optional
    }

    public CoffeebatchEntity save(CoffeebatchEntity coffeebatch) {
        return coffeebatchRepository.save(coffeebatch);
    }

    public void deleteById(Long id) {
        coffeebatchRepository.deleteById(id);
    }
}
