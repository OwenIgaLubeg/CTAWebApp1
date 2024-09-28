package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.CoffeebatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeebatchRepository extends JpaRepository<CoffeebatchEntity, Long> {
}
