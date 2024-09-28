package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.IntermediaryEntity;
import com.owen.CoffeeTraceabilityApp.repository.IntermediaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntermediaryService {

    @Autowired
    private IntermediaryRepository intermediaryRepository;

    public List<IntermediaryEntity> getAllIntermediaries() {
        return intermediaryRepository.findAll();
    }

    public Optional<IntermediaryEntity> getIntermediaryById(Long id) {
        return intermediaryRepository.findById(id);
    }

    public IntermediaryEntity createOrUpdateIntermediary(IntermediaryEntity intermediaryEntity) {
        return intermediaryRepository.save(intermediaryEntity);
    }

    public void deleteIntermediary(Long id) {
        intermediaryRepository.deleteById(id);
    }

    public List<IntermediaryEntity> getIntermediariesByRegion(String region) {
        return intermediaryRepository.findByRegion(region);
    }

    public List<IntermediaryEntity> findIntermediariesByNameAndRegion(String name, String region) {
        return intermediaryRepository.findByNameAndRegion(name, region);
    }

    public List<IntermediaryEntity> getIntermediariesByUserId(Long userId) {
        return intermediaryRepository.findByUser_UserId(userId);
    }

    public void saveIntermediary(IntermediaryEntity intermediary) {
        intermediaryRepository.save(intermediary);
    }
}

