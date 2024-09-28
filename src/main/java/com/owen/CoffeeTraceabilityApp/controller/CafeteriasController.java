package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.Cafeterias;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.CafeteriasService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cafeterias")
public class CafeteriasController {

    @Autowired
    private CafeteriasService cafeteriasService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllCafeterias(Model model) {
        List<Cafeterias> cafeteriasList = cafeteriasService.getAllCafeterias();
        model.addAttribute("cafeterias", cafeteriasList);
        return "cafeterias/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("cafeteria", new Cafeterias());
        return "cafeterias/create";
    }

    // Save a new Cafeteria and create the corresponding UserEntity
    @PostMapping("/new")
    public String createCafeteria(@ModelAttribute("cafeteria") Cafeterias cafeteria) {
        UserEntity user = new UserEntity();
        user.setName(cafeteria.getName());
        user.setEmail(cafeteria.getEmail());
        user.setPhoneNumber(cafeteria.getPhoneNumber());
        user.setLocation(cafeteria.getLocation());
        user.setRole("cafeteria");  
        userService.saveUser(user);  

        cafeteria.setUser(user);
        cafeteriasService.saveCafeteria(cafeteria);  
        return "redirect:/cafeterias";
    }

    @GetMapping("/{id}")
    public String getCafeteriaById(@PathVariable Long id, Model model) {
        Cafeterias cafeteria = cafeteriasService.getCafeteriaById(id)
                .orElseThrow(() -> new RuntimeException("Cafeteria not found"));
        model.addAttribute("cafeteria", cafeteria);
        return "cafeterias/detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Cafeterias cafeteria = cafeteriasService.getCafeteriaById(id)
                .orElseThrow(() -> new RuntimeException("Cafeteria not found"));
        model.addAttribute("cafeteria", cafeteria);
        return "cafeterias/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCafeteria(@PathVariable Long id, @ModelAttribute("cafeteria") Cafeterias cafeteriaDetails) {
        cafeteriasService.updateCafeteria(id, cafeteriaDetails);
        return "redirect:/cafeterias";
    }

    @PostMapping("/delete/{id}")
    public String deleteCafeteria(@PathVariable Long id) {
        cafeteriasService.deleteCafeteria(id);
        return "redirect:/cafeterias";
    }
}
