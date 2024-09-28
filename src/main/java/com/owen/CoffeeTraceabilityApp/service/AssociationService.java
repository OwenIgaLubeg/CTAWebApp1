package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.AssociationEntity;
import com.owen.CoffeeTraceabilityApp.repository.AssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associationRepository;

    public List<AssociationEntity> getAllAssociations() {
        return associationRepository.findAll();
    }

    public Optional<AssociationEntity> getAssociationById(Long id) {
        return associationRepository.findById(id);  // Proper handling with Optional
    }

    public void saveOrUpdateAssociation(AssociationEntity association) {
        associationRepository.save(association);
    }

    public void deleteAssociation(Long id) {
        associationRepository.deleteById(id);
    }
}

