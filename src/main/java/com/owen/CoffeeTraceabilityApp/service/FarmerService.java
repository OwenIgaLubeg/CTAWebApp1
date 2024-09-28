package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.FarmerEntity;
import com.owen.CoffeeTraceabilityApp.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public List<FarmerEntity> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Optional<FarmerEntity> getFarmerById(Long farmerId) {
        return farmerRepository.findById(farmerId);
    }

    public FarmerEntity createFarmer(FarmerEntity farmer) {
        return farmerRepository.save(farmer);
    }

    public Optional<FarmerEntity> updateFarmer(Long farmerId, FarmerEntity updatedFarmer) {
        if (farmerRepository.existsById(farmerId)) {
            updatedFarmer.setFarmerId(farmerId);
            return Optional.of(farmerRepository.save(updatedFarmer));
        }
        return Optional.empty(); // Return empty Optional instead of null
    }

    public void deleteFarmer(Long farmerId) {
        farmerRepository.deleteById(farmerId);
    }

    public void saveFarmer(FarmerEntity farmer) {
        farmerRepository.save(farmer);
    }
}

