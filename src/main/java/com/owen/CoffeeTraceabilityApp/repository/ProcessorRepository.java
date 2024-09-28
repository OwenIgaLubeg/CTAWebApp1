package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.ProcessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<ProcessorEntity, Long> {
}