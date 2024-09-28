package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.RoastingFirms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoastingFirmsRepository extends JpaRepository<RoastingFirms, Long> {
  
    RoastingFirms findByName(String name);
    RoastingFirms findByPhoneNumber(String phoneNumber);
}
