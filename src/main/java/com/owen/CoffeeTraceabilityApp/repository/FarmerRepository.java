package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.FarmerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<FarmerEntity, Long> {
}
