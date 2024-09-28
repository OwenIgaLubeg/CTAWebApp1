package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.ProcessorEntity;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.ProcessorService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;
    
    @Autowired
    private UserService userService;


    @GetMapping
    public String getAllProcessors(Model model) {
        model.addAttribute("processors", processorService.getAllProcessors());
        return "processors/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("processor", new ProcessorEntity());
        return "processors/create";
    }

 // Save a new Processor and create the corresponding UserEntity
    @PostMapping("/create")
    public String createProcessor(@ModelAttribute("processors") ProcessorEntity processor) {
        // Create a corresponding UserEntity and save it
        UserEntity user = new UserEntity();
        user.setName(processor.getName());
        user.setEmail(processor.getEmail());
        user.setPhoneNumber(processor.getPhoneNumber());
        user.setLocation(processor.getLocation());
        user.setRole("processor");  
        userService.saveUser(user);  

        processor.setUser(user);
        processorService.saveProcessor(processor);  

        return "redirect:/processors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<ProcessorEntity> processor = processorService.getProcessorById(id);
        if (processor.isPresent()) {
            model.addAttribute("processor", processor.get());
            return "processors/edit";
        }
        return "redirect:/processors";
    }

    @PostMapping("/edit/{id}")
    public String updateProcessor(@PathVariable Long id, @ModelAttribute ProcessorEntity processor) {
        processor.setProcessorId(id);
        processorService.saveProcessor(processor);
        return "redirect:/processors";
    }

    @GetMapping("/delete/{id}")
    public String deleteProcessor(@PathVariable Long id) {
        processorService.deleteProcessor(id);
        return "redirect:/processors";
    }
}