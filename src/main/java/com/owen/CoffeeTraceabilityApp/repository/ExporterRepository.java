package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.ExporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExporterRepository extends JpaRepository<ExporterEntity, Long> {
    
    List<ExporterEntity> findByNameContainingIgnoreCase(String name);
}
