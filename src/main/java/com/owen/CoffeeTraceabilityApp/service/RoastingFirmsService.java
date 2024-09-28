package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.RoastingFirms;
import com.owen.CoffeeTraceabilityApp.repository.RoastingFirmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoastingFirmsService {

    @Autowired
    private RoastingFirmsRepository roastingFirmsRepository;

    public List<RoastingFirms> getAllRoastingFirms() {
        return roastingFirmsRepository.findAll();
    }

    public Optional<RoastingFirms> getRoastingFirmById(Long id) {
        return roastingFirmsRepository.findById(id);
    }

    public RoastingFirms addRoastingFirm(RoastingFirms roastingFirm) {
        return roastingFirmsRepository.save(roastingFirm);
    }

    public RoastingFirms updateRoastingFirm(Long id, RoastingFirms roastingFirmDetails) {
        RoastingFirms roastingFirm = roastingFirmsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Roasting Firm not found"));
        roastingFirm.setName(roastingFirmDetails.getName());
        roastingFirm.setPhoneNumber(roastingFirmDetails.getPhoneNumber());
        roastingFirm.setEmail(roastingFirmDetails.getEmail());
        roastingFirm.setLocation(roastingFirmDetails.getLocation());
        roastingFirm.setRoastProfiles(roastingFirmDetails.getRoastProfiles());
        roastingFirm.setUser(roastingFirmDetails.getUser());
        return roastingFirmsRepository.save(roastingFirm);
    }

    public void deleteRoastingFirm(Long id) {
        roastingFirmsRepository.deleteById(id);
    }

    public void saveRoaster(RoastingFirms roastingFirm) {
        roastingFirmsRepository.save(roastingFirm);
    }
}

