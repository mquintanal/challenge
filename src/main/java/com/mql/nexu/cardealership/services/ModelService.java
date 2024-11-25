package com.mql.nexu.cardealership.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mql.nexu.cardealership.models.Brand;
import com.mql.nexu.cardealership.models.Model;
import com.mql.nexu.cardealership.repositories.BrandRepository;
import com.mql.nexu.cardealership.repositories.ModelRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ModelService {
	
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Lista todos los modelos de una marca.
     *
     * @param brandId ID de la marca.
     * @return Lista de modelos asociados a la marca.
     * @throws EntityNotFoundException si la marca no existe.
     */
    public List<Model> getModelsByBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        return brand.getModels();
    }

    /**
     * Agrega un nuevo modelo a una marca existente.
     *
     * @param brandId ID de la marca.
     * @param name Nombre del modelo.
     * @param averagePrice Precio promedio del modelo.
     * @return El modelo creado.
     * @throws IllegalArgumentException si el modelo ya existe en la marca o el precio no es válido.
     */
    public Model addModelToBrand(Long brandId, String name, Double averagePrice) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand not found"));

        if (brand.getModels().stream().anyMatch(model -> model.getName().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException("Model name already exists for this brand");
        }

        if (averagePrice != null && averagePrice < 100000) {
            throw new IllegalArgumentException("Average price must be greater than 100,000");
        }

        Model model = new Model();
        model.setBrand(brand);
        model.setName(name);
        model.setAveragePrice(averagePrice);
        return modelRepository.save(model);
    }
    /**
     * Actualiza el precio promedio de un modelo.
     *
     * @param modelId ID del modelo.
     * @param newAveragePrice Nuevo precio promedio.
     * @return El modelo actualizado.
     * @throws EntityNotFoundException si el modelo no existe.
     * @throws IllegalArgumentException si el precio no es válido.
     */
    public Model updateModelAveragePrice(Long modelId, Double newAveragePrice) {
        if (newAveragePrice == null || newAveragePrice < 100000) {
            throw new IllegalArgumentException("Average price must be greater than 100,000");
        }

        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + modelId));

        model.setAveragePrice(newAveragePrice);
        return modelRepository.save(model);
    }
    
    /**
     * Filtra los modelos según un rango de precios promedio.
     *
     * @param greater Precio mínimo.
     * @param lower Precio máximo.
     * @return Lista de modelos en el rango.
     */
    public List<Model> getModelsByPriceRange(Double greater, Double lower) {
        return modelRepository.findByAveragePriceBetween(greater, lower);
    }

    /**
     * Lista todos los modelos con un precio promedio mayor al indicado.
     *
     * @param price Precio mínimo.
     * @return Lista de modelos.
     */
    public List<Model> getModelsByPriceGreater(Double price) {
        return modelRepository.findByAveragePriceGreaterThan(price);
    }

    /**
     * Lista todos los modelos con un precio promedio menor al indicado.
     *
     * @param price Precio máximo.
     * @return Lista de modelos.
     */
    public List<Model> getModelsByPriceLower(Double price) {
        return modelRepository.findByAveragePriceLessThan(price);
    }
    
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }
    
    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found with id: " + id));
    }
}
