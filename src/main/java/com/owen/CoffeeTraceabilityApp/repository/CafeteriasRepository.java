package com.owen.CoffeeTraceabilityApp.repository;

import com.owen.CoffeeTraceabilityApp.entity.Cafeterias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeteriasRepository extends JpaRepository<Cafeterias, Long> {
    Cafeterias findByName(String name);
    Cafeterias findByPhoneNumber(String phoneNumber);
}
