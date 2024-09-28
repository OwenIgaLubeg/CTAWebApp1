package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.FarmerEntity;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.FarmerService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private UserService userService;

    // List all farmers
    @GetMapping
    public String listFarmers(Model model) {
        List<FarmerEntity> farmers = farmerService.getAllFarmers();
        model.addAttribute("farmers", farmers);
        return "farmers/farmer-list";  // Ensure farmer-list.html exists in templates/farmers/
    }

    // View specific farmer
    @GetMapping("/{id}")
    public String viewFarmer(@PathVariable Long id, Model model) {
        FarmerEntity farmer = farmerService.getFarmerById(id).orElse(null);
        if (farmer == null) {
            return "error/404";  // Handle case where farmer not found
        }
        model.addAttribute("farmer", farmer);
        return "farmers/view_farmer";  // Ensure view_farmer.html exists
    }

    // Show form to create a new farmer
    @GetMapping("/create")
    public String showCreateFarmerForm(Model model) {
        model.addAttribute("farmer", new FarmerEntity());
        return "farmers/create-farmer";  // Ensure create-farmer.html exists
    }

    // Save new farmer and corresponding UserEntity
    @PostMapping("/create")
    public String createFarmer(@ModelAttribute("farmer") FarmerEntity farmer, Model model) {
        // Check for existing email and phone number in User table
        if (userService.emailExists(farmer.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "farmers/create-farmer";
        }
        if (userService.phoneNumberExists(farmer.getPhoneNumber())) {
            model.addAttribute("error", "Phone number already exists");
            return "farmers/create-farmer";
        }

        // Create UserEntity
        UserEntity user = new UserEntity();
        user.setName(farmer.getName());
        user.setEmail(farmer.getEmail());
        user.setPhoneNumber(farmer.getPhoneNumber());
        user.setLocation(farmer.getLocation());
        user.setRole("farmer");
        userService.saveUser(user);

        farmer.setUser(user);
        farmerService.saveFarmer(farmer);

        return "redirect:/farmers";
    }

    // Edit farmer
    @GetMapping("/edit/{id}")
    public String showEditFarmerForm(@PathVariable Long id, Model model) {
        FarmerEntity farmer = farmerService.getFarmerById(id).orElse(null);
        if (farmer == null) {
            return "error/404";
        }
        model.addAttribute("farmer", farmer);
        return "farmers/edit_farmer";  // Ensure edit_farmer.html exists
    }

    // Update farmer
    @PostMapping("/edit/{id}")
    public String updateFarmer(@PathVariable Long id, @ModelAttribute("farmer") FarmerEntity farmer) {
        farmer.setFarmerId(id);
        farmerService.saveFarmer(farmer);
        return "redirect:/farmers";
    }

    // Delete farmer
    @GetMapping("/delete/{id}")
    public String deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return "redirect:/farmers";
    }
}
