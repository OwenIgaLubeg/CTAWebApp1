package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.AssociationEntity;
import com.owen.CoffeeTraceabilityApp.entity.UserEntity;
import com.owen.CoffeeTraceabilityApp.service.AssociationService;
import com.owen.CoffeeTraceabilityApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/associations")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @Autowired
    private UserService userService;

    // List all associations
    @GetMapping
    public String listAssociations(Model model) {
        List<AssociationEntity> associations = associationService.getAllAssociations();
        model.addAttribute("listAssociations", associations);  // Changed to match HTML template
        return "associations/associations";
    }

    // Show form to create a new association
    @GetMapping("/new")
    public String showCreateAssociationForm(Model model) {
        model.addAttribute("association", new AssociationEntity());
        return "associations/new_association";
    }

    // Save new association and corresponding UserEntity
    @PostMapping("/new")
    public String createAssociation(@ModelAttribute("association") AssociationEntity association, Model model) {
        if (userService.emailExists(association.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "associations/new_association";
        }

        if (userService.phoneNumberExists(association.getPhoneNumber())) {
            model.addAttribute("error", "Phone number already exists");
            return "associations/new_association";
        }

        UserEntity user = new UserEntity();
        user.setName(association.getName());
        user.setEmail(association.getEmail());
        user.setPhoneNumber(association.getPhoneNumber());
        user.setRole("association");
        userService.saveUser(user);

        association.setUser(user);
        associationService.saveOrUpdateAssociation(association);

        return "redirect:/associations";
    }

    // Edit association
    @GetMapping("/edit/{id}")
    public String showEditAssociationForm(@PathVariable Long id, Model model) {
        Optional<AssociationEntity> association = associationService.getAssociationById(id);
        if (association.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("association", association.get());  // Fix for Optional
        return "associations/edit_association";
    }

    // Update association
    @PostMapping("/edit/{id}")
    public String updateAssociation(@PathVariable Long id, @ModelAttribute("association") AssociationEntity association) {
        association.setAssociationId(id);
        associationService.saveOrUpdateAssociation(association);
        return "redirect:/associations";
    }

    // Delete association
    @GetMapping("/delete/{id}")
    public String deleteAssociation(@PathVariable Long id) {
        associationService.deleteAssociation(id);
        return "redirect:/associations";
    }
}
