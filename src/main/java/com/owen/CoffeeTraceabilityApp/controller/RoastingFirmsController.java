package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.RoastingFirms;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.RoastingFirmsService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roastingfirms")
public class RoastingFirmsController {

    @Autowired
    private RoastingFirmsService roastingFirmsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllRoastingFirms(Model model) {
        List<RoastingFirms> roastingFirmsList = roastingFirmsService.getAllRoastingFirms();
        model.addAttribute("roastingFirms", roastingFirmsList);
        return "roastingfirms/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("roastingFirm", new RoastingFirms());
        return "roastingfirms/create";
    }

    // Save a new RoastingFirm and create the corresponding UserEntity
    @PostMapping("/new")
    public String createRoastingFirm(@ModelAttribute("roastingFirm") RoastingFirms roastingFirm) {
        // Create a corresponding UserEntity and save it
        UserEntity user = new UserEntity();
        user.setName(roastingFirm.getName());
        user.setEmail(roastingFirm.getEmail());
        user.setPhoneNumber(roastingFirm.getPhoneNumber());
        user.setLocation(roastingFirm.getLocation());
        user.setRole("roastingFirm");
        userService.saveUser(user);

        roastingFirm.setUser(user);
        roastingFirmsService.saveRoaster(roastingFirm);
        return "redirect:/roastingfirms";
    }

    @GetMapping("/{id}")
    public String getRoastingFirmById(@PathVariable Long id, Model model) {
        RoastingFirms roastingFirm = roastingFirmsService.getRoastingFirmById(id)
                .orElseThrow(() -> new RuntimeException("Roasting Firm not found"));
        model.addAttribute("roastingFirm", roastingFirm);
        return "roastingfirms/detail";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        RoastingFirms roastingFirm = roastingFirmsService.getRoastingFirmById(id)
                .orElseThrow(() -> new RuntimeException("Roasting Firm not found"));
        model.addAttribute("roastingFirm", roastingFirm);
        return "roastingfirms/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRoastingFirm(@PathVariable Long id, @ModelAttribute("roastingFirm") RoastingFirms roastingFirmDetails) {
        roastingFirmsService.updateRoastingFirm(id, roastingFirmDetails);
        return "redirect:/roastingfirms";
    }

    @PostMapping("/delete/{id}")
    public String deleteRoastingFirm(@PathVariable Long id) {
        roastingFirmsService.deleteRoastingFirm(id);
        return "redirect:/roastingfirms";
    }
}

