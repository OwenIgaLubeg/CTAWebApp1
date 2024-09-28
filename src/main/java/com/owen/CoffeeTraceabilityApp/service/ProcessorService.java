package com.owen.CoffeeTraceabilityApp.service;

import com.owen.CoffeeTraceabilityApp.entity.ProcessorEntity;
import com.owen.CoffeeTraceabilityApp.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {

    @Autowired
    private ProcessorRepository processorRepository;

    public List<ProcessorEntity> getAllProcessors() {
        return processorRepository.findAll();
    }

    public Optional<ProcessorEntity> getProcessorById(Long id) {
        return processorRepository.findById(id);
    }

    public ProcessorEntity saveProcessor(ProcessorEntity processor) {
        return processorRepository.save(processor);
    }

    public void deleteProcessor(Long id) {
        processorRepository.deleteById(id);
    }
    
}