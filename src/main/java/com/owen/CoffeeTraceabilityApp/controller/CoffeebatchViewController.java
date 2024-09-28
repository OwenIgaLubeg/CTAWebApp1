package com.owen.CoffeeTraceabilityApp.controller;

import com.owen.CoffeeTraceabilityApp.entity.*;
import com.owen.CoffeeTraceabilityApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/coffeebatch")
public class CoffeebatchViewController {

    @Autowired
    private CoffeebatchService coffeebatchService;

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private IntermediaryService intermediaryService;

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ExporterService exporterService;

    @Autowired
    private RoastingFirmsService roastingFirmsService;

    @Autowired
    private AssociationService associationService;

    @Autowired
    private CafeteriasService cafeteriasService;

    // List all coffee batches
    @GetMapping
    public String getAllCoffeebatches(Model model) {
        model.addAttribute("coffeebatches", coffeebatchService.findAll());
        return "coffeebatch/coffeebatches_list";
    }

    // View a specific coffee batch
    @GetMapping("/{id}")
    public String viewCoffeebatch(@PathVariable Long id, Model model) {
        Optional<CoffeebatchEntity> coffeebatch = coffeebatchService.findById(id);
        if (coffeebatch.isPresent()) {
            model.addAttribute("coffeebatch", coffeebatch.get());
            return "coffeebatch/view_coffeebatch";
        } else {
            return "error/404";  // This view should exist
        }
    }

    // Form to create a new coffee batch
    @GetMapping("/create")
    public String createCoffeebatchForm(Model model) {
        model.addAttribute("coffeebatch", new CoffeebatchEntity());
        model.addAttribute("farmers", farmerService.getAllFarmers());
        model.addAttribute("intermediaries", intermediaryService.getAllIntermediaries());
        model.addAttribute("processors", processorService.getAllProcessors());
        model.addAttribute("exporters", exporterService.getAllExporters());
        model.addAttribute("roasters", roastingFirmsService.getAllRoastingFirms());
        model.addAttribute("associations", associationService.getAllAssociations());
        model.addAttribute("cafeterias", cafeteriasService.getAllCafeterias());

        return "coffeebatch/create_coffeebatch";  // Make sure this view exists
    }

    // Handle POST request for creating a new coffee batch
    @PostMapping("/create")
    public String createCoffeebatch(@ModelAttribute("coffeebatch") CoffeebatchEntity coffeebatch,
                                    @RequestParam("farmerId") Long farmerId,
                                    @RequestParam(value = "intermediaryId", required = false) Long intermediaryId,
                                    @RequestParam(value = "processorId", required = false) Long processorId,
                                    @RequestParam(value = "exporterId", required = false) Long exporterId,
                                    @RequestParam(value = "roasterId", required = false) Long roasterId,
                                    @RequestParam(value = "associationId", required = false) Long associationId,
                                    @RequestParam(value = "cafeteriaId", required = false) Long cafeteriaId,
                                    Model model) {

        // Set farmer, intermediary, etc., handling null values safely
        FarmerEntity farmer = farmerService.getFarmerById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid farmer ID: " + farmerId));
        coffeebatch.setFarmer(farmer);

        // Optional relationships
        if (intermediaryId != null) {
            IntermediaryEntity intermediary = intermediaryService.getIntermediaryById(intermediaryId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid intermediary ID: " + intermediaryId));
            coffeebatch.setIntermediary(intermediary);
        }

        if (processorId != null) {
            ProcessorEntity processor = processorService.getProcessorById(processorId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid processor ID: " + processorId));
            coffeebatch.setProcessor(processor);
        }

        if (exporterId != null) {
            ExporterEntity exporter = exporterService.getExporterById(exporterId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid exporter ID: " + exporterId));
            coffeebatch.setExporter(exporter);
        }

        if (roasterId != null) {
            RoastingFirms roaster = roastingFirmsService.getRoastingFirmById(roasterId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid roaster ID: " + roasterId));
            coffeebatch.setRoaster(roaster);
        }

        if (associationId != null) {
            AssociationEntity association = associationService.getAssociationById(associationId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid association ID: " + associationId));
            coffeebatch.setAssociation(association);
        }

        if (cafeteriaId != null) {
            Cafeterias cafeteria = cafeteriasService.getCafeteriaById(cafeteriaId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid cafeteria ID: " + cafeteriaId));
            coffeebatch.setCafeteria(cafeteria);
        }

        coffeebatch.setCurrentStatus("Initiated");
        coffeebatch.setTrackingHistory("Batch initiated by " + farmer.getName());
        coffeebatchService.save(coffeebatch);

        return "redirect:/coffeebatch";  // Redirect to batch list after creation
    }

    // Form to edit an existing coffee batch
    @GetMapping("/edit/{id}")
    public String editCoffeebatchForm(@PathVariable Long id, Model model) {
        Optional<CoffeebatchEntity> coffeebatch = coffeebatchService.findById(id);
        if (coffeebatch.isPresent()) {
            model.addAttribute("coffeebatch", coffeebatch.get());
            model.addAttribute("farmers", farmerService.getAllFarmers());
            model.addAttribute("intermediaries", intermediaryService.getAllIntermediaries());
            model.addAttribute("processors", processorService.getAllProcessors());
            model.addAttribute("exporters", exporterService.getAllExporters());
            model.addAttribute("roasters", roastingFirmsService.getAllRoastingFirms());
            model.addAttribute("associations", associationService.getAllAssociations());
            model.addAttribute("cafeterias", cafeteriasService.getAllCafeterias());

            return "coffeebatch/edit_coffeebatch";
        } else {
            return "error/404";  // Show 404 page if batch not found
        }
    }

    // Update an existing coffee batch
    @PostMapping("/edit/{id}")
    public String updateCoffeebatch(@PathVariable Long id, @ModelAttribute("coffeebatch") CoffeebatchEntity coffeebatch) {
        coffeebatch.setBatchId(id);
        coffeebatchService.save(coffeebatch);
        return "redirect:/coffeebatch";
    }

    // Delete a coffee batch
    @GetMapping("/delete/{id}")
    public String deleteCoffeebatch(@PathVariable Long id) {
        coffeebatchService.deleteById(id);
        return "redirect:/coffeebatch";
    }
     // Search for a coffee batch by batch ID
    @GetMapping("/search")
    public String searchBatch(@RequestParam("batchId") Long batchId, Model model) {
        Optional<CoffeebatchEntity> coffeebatch = coffeebatchService.findById(batchId);
        if (coffeebatch.isPresent()) {
            model.addAttribute("coffeebatch", coffeebatch.get());
            return "coffeebatch/view_coffeebatch";
        } else {
            model.addAttribute("error", "Batch not found!");
            return "coffeebatch/coffeebatches_list";
        }
    }
}
