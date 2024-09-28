package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.IntermediaryEntity;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.IntermediaryService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/intermediaries")
public class IntermediaryController {

    @Autowired
    private IntermediaryService intermediaryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listIntermediaries(Model model) {
        List<IntermediaryEntity> intermediaries = intermediaryService.getAllIntermediaries();
        model.addAttribute("intermediaries", intermediaries);
        return "intermediaries/intermediary-list";  // Ensure intermediary-list.html exists in templates/intermediaries
    }

    @GetMapping("/{id}")
    public String viewIntermediary(@PathVariable Long id, Model model) {
        Optional<IntermediaryEntity> intermediary = intermediaryService.getIntermediaryById(id);
        if (intermediary.isEmpty()) {
            return "error/404";  // Handle case where intermediary not found
        }
        model.addAttribute("intermediary", intermediary.get());
        return "intermediaries/intermediary-view";  // Ensure intermediary-view.html exists
    }

    @GetMapping("/new")
    public String showCreateIntermediaryForm(Model model) {
        model.addAttribute("intermediary", new IntermediaryEntity());  // Provide a new empty IntermediaryEntity
        return "intermediaries/intermediary-form";  // Ensure this template exists in the correct folder
    }

    @PostMapping("/new")
    public String createIntermediary(@ModelAttribute("intermediary") IntermediaryEntity intermediary, Model model) {
        // Check for existing email or phone number in User table
        if (userService.emailExists(intermediary.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "intermediaries/intermediary-form";  // Return form with error
        }
        if (userService.phoneNumberExists(intermediary.getPhoneNumber())) {
            model.addAttribute("error", "Phone number already exists");
            return "intermediaries/intermediary-form";  // Return form with error
        }

        // Create UserEntity for the intermediary
        UserEntity user = new UserEntity();
        user.setName(intermediary.getName());
        user.setEmail(intermediary.getEmail());
        user.setPhoneNumber(intermediary.getPhoneNumber());
        user.setLocation(intermediary.getRegion());  // Assuming 'region' maps to user location
        user.setRole("intermediary");  // Set role as 'intermediary'
        userService.saveUser(user);  // Save the user in the user table

        // Set the user for the intermediary and save it
        intermediary.setUser(user);
        intermediaryService.saveIntermediary(intermediary);

        return "redirect:/intermediaries";
    }


    @GetMapping("/edit/{id}")
    public String showEditIntermediaryForm(@PathVariable Long id, Model model) {
        Optional<IntermediaryEntity> intermediary = intermediaryService.getIntermediaryById(id);
        if (intermediary.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("intermediary", intermediary.get());
        return "intermediaries/intermediary-form";
    }

    @PostMapping("/edit/{id}")
    public String updateIntermediary(@PathVariable Long id, @ModelAttribute("intermediary") IntermediaryEntity intermediary) {
        intermediary.setIntermediaryId(id);
        intermediaryService.saveIntermediary(intermediary);
        return "redirect:/intermediaries";
    }

    @GetMapping("/delete/{id}")
    public String deleteIntermediary(@PathVariable Long id) {
        intermediaryService.deleteIntermediary(id);
        return "redirect:/intermediaries";
    }
}
