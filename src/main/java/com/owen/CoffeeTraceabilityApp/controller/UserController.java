package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.*;
import com.owen.CoffeeTraceabilityApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ExporterService exporterService;

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private IntermediaryService intermediaryService;

    @Autowired
    private RoastingFirmsService roastingFirmsService;

    @Autowired
    private CafeteriasService cafeteriasService;


    @GetMapping
    public String listUsers(Model model) {
        List<UserEntity> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "createUser";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        Optional<UserEntity> user = userService.getUserById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "editUser";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") UserEntity user) {
        user.setUserId(id);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserEntity user, Model model) {
        // Check for existing email
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "register";  
        }

        // Check for existing phone number
        if (userService.phoneNumberExists(user.getPhoneNumber())) {
            model.addAttribute("error", "Phone number already exists");
            return "register";  
        }

        // Save the user to the users table
        userService.saveUser(user);

        // Determine the role and save the user in the respective role table
        String role = user.getRole().toLowerCase();
        switch (role) {
            case "farmer":
                FarmerEntity farmer = new FarmerEntity();
                farmer.setName(user.getName());
                farmer.setPhoneNumber(user.getPhoneNumber());
                farmer.setEmail(user.getEmail());
                farmer.setLocation(user.getLocation());
                farmer.setUser(user);
                farmerService.saveFarmer(farmer);
                break;

            case "exporter":
                ExporterEntity exporter = new ExporterEntity();
                exporter.setName(user.getName());
                exporter.setPhoneNumber(user.getPhoneNumber());
                exporter.setEmail(user.getEmail());
                exporter.setLocation(user.getLocation());
                exporter.setUser(user);
                exporterService.saveExporter(exporter);
                break;

            case "processor":
                ProcessorEntity processor = new ProcessorEntity();
                processor.setName(user.getName());
                processor.setPhoneNumber(user.getPhoneNumber());
                processor.setEmail(user.getEmail());
                processor.setLocation(user.getLocation());
                processor.setUser(user);
                processorService.saveProcessor(processor);
                break;

            case "intermediary":
                IntermediaryEntity intermediary = new IntermediaryEntity();
                intermediary.setName(user.getName());
                intermediary.setPhoneNumber(user.getPhoneNumber());
                intermediary.setEmail(user.getEmail());
                intermediary.setRegion(user.getLocation());  
                intermediary.setUser(user);
                intermediaryService.saveIntermediary(intermediary);
                break;

            case "cafeteria":
                Cafeterias cafeteria = new Cafeterias();
                cafeteria.setName(user.getName());
                cafeteria.setPhoneNumber(user.getPhoneNumber());
                cafeteria.setEmail(user.getEmail());
                cafeteria.setLocation(user.getLocation());
                cafeteria.setUser(user);
                cafeteriasService.saveCafeteria(cafeteria);
                break;

            case "roaster":
                RoastingFirms roaster = new RoastingFirms();
                roaster.setName(user.getName());
                roaster.setPhoneNumber(user.getPhoneNumber());
                roaster.setEmail(user.getEmail());
                roaster.setLocation(user.getLocation());
                roaster.setUser(user);
                roastingFirmsService.saveRoaster(roaster);
                break;

            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }

        return "redirect:/users/login";
    }

 
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<UserEntity> user = userService.findByName(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Authentication successful, redirect to home page
            return "redirect:/"; 
        } else {
            // Authentication failed, reload login page with an error
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

