package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.ExporterEntity;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.ExporterService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exporters")
public class ExporterController {

    @Autowired
    private ExporterService exporterService;
    
    @Autowired
    private UserService userService;

    // List all exporters
    @GetMapping
    public String getAllExporters(Model model) {
        List<ExporterEntity> exporters = exporterService.getAllExporters();
        model.addAttribute("exporters", exporters);
        return "exporters/list-exporters"; // Ensure HTML file exists
    }

    // Show form to create a new exporter
    @GetMapping("/new")
    public String showCreateExporterForm(Model model) {
        model.addAttribute("exporter", new ExporterEntity());
        return "exporters/create-exporter"; // Ensure create-exporter.html exists
    }

    // Save a new exporter and create corresponding UserEntity
    @PostMapping("/new")
    public String createExporter(@ModelAttribute("exporter") ExporterEntity exporter) {
        // Create and save UserEntity
        UserEntity user = new UserEntity();
        user.setName(exporter.getName());
        user.setEmail(exporter.getEmail());
        user.setPhoneNumber(exporter.getPhoneNumber());
        user.setLocation(exporter.getLocation());
        user.setRole("exporter");  // Set user role as 'exporter'
        userService.saveUser(user);

        // Link exporter to user
        exporter.setUser(user);
        exporterService.saveExporter(exporter);

        return "redirect:/exporters";
    }

    // Show form to update an exporter
    @GetMapping("/edit/{id}")
    public String showUpdateExporterForm(@PathVariable("id") Long id, Model model) {
        Optional<ExporterEntity> exporter = exporterService.getExporterById(id);
        if (exporter.isPresent()) {
            model.addAttribute("exporter", exporter.get());
            return "exporters/update-exporter"; // Ensure update-exporter.html exists
        } else {
            return "error/404";
        }
    }

    // Update an exporter
    @PostMapping("/edit/{id}")
    public String updateExporter(@PathVariable("id") Long id, @ModelAttribute("exporter") ExporterEntity exporter) {
        exporter.setExporterId(id);
        exporterService.updateExporter(exporter);
        return "redirect:/exporters";
    }

    // Delete an exporter
    @GetMapping("/delete/{id}")
    public String deleteExporter(@PathVariable("id") Long id) {
        exporterService.deleteExporter(id);
        return "redirect:/exporters";
    }

    // View exporter details
    @GetMapping("/{id}")
    public String getExporterById(@PathVariable("id") Long id, Model model) {
        Optional<ExporterEntity> exporter = exporterService.getExporterById(id);
        if (exporter.isPresent()) {
            model.addAttribute("exporter", exporter.get());
            return "exporters/view-exporter"; // Ensure view-exporter.html exists
        } else {
            return "error/404";
        }
    }

    // Search for exporters by name
    @GetMapping("/search")
    public String searchExporters(@RequestParam("name") String name, Model model) {
        List<ExporterEntity> exporters = exporterService.findExportersByName(name);
        model.addAttribute("exporters", exporters);
        return "exporters/list-exporters";
    }
}
