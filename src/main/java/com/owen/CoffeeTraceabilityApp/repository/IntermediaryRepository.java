package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.IntermediaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntermediaryRepository extends JpaRepository<IntermediaryEntity, Long> {

    List<IntermediaryEntity> findByRegion(String region);

    List<IntermediaryEntity> findByNameAndRegion(String name, String region);

    List<IntermediaryEntity> findByUser_UserId(Long userId);
}
