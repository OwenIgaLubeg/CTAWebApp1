package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.ExporterEntity;
import com.owen.CoffeeTraceabilityApp.repository.ExporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExporterService {

    @Autowired
    private ExporterRepository exporterRepository;

    public List<ExporterEntity> getAllExporters() {
        return exporterRepository.findAll();
    }

    public Optional<ExporterEntity> getExporterById(Long id) {
        return exporterRepository.findById(id); // Return Optional instead of null
    }

    public void saveExporter(ExporterEntity exporter) {
        exporterRepository.save(exporter);
    }

    public void updateExporter(ExporterEntity exporter) {
        exporterRepository.save(exporter);
    }

    public void deleteExporter(Long id) {
        exporterRepository.deleteById(id);
    }

    public List<ExporterEntity> findExportersByName(String name) {
        return exporterRepository.findByNameContainingIgnoreCase(name);
    }
}

