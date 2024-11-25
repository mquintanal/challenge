package com.mql.nexu.cardealership.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mql.nexu.cardealership.models.Model;
import com.mql.nexu.cardealership.services.ModelService;

@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PutMapping("/{id}")
    public Model updateModel(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        Double averagePrice = body.get("average_price");
        if (averagePrice == null || averagePrice < 100000) {
            throw new IllegalArgumentException("Average price must be greater than 100,000");
        }

        Model model = modelService.getModelById(id);
        model.setAveragePrice(averagePrice);
        return modelService.saveModel(model);
    }

    @GetMapping
    public List<Model> getModels(@RequestParam(required = false) Double greater, @RequestParam(required = false) Double lower) {
        if (greater != null && lower != null) {
            return modelService.getModelsByPriceRange(greater, lower);
        } else if (greater != null) {
            return modelService.getModelsByPriceGreater(greater);
        } else if (lower != null) {
            return modelService.getModelsByPriceLower(lower);
        }
        return modelService.getAllModels();
    }
}
