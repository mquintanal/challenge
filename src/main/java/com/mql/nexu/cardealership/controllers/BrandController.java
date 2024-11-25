package com.mql.nexu.cardealership.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mql.nexu.cardealership.models.Brand;
import com.mql.nexu.cardealership.models.Model;
import com.mql.nexu.cardealership.services.BrandService;
import com.mql.nexu.cardealership.services.ModelService;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private ModelService modelService;

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}/models")
    public List<Model> getModelsByBrand(@PathVariable Long id) {
        return modelService.getModelsByBrand(id);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Map<String, String> body) {
        return brandService.createBrand(body.get("name"));
    }
}
