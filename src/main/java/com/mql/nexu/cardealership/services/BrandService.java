package com.mql.nexu.cardealership.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mql.nexu.cardealership.models.Brand;
import com.mql.nexu.cardealership.repositories.BrandRepository;
import java.util.NoSuchElementException;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    /**
     * Lista todas las marcas disponibles.
     *
     * @return Lista de marcas.
     */
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    /**
     * Obtiene una marca por su ID.
     *
     * @param id ID de la marca.
     * @return La marca correspondiente.
     * @throws EntityNotFoundException si no se encuentra la marca.
     */
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Brand not found with id: " + id));
    }

    /**
     * Crea una nueva marca si no existe una con el mismo nombre.
     *
     * @param name Nombre de la marca.
     * @return La marca creada.
     * @throws IllegalArgumentException si ya existe una marca con el mismo nombre.
     */
    public Brand createBrand(String name) {
        if (brandRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Brand name already exists: " + name);
        }
        Brand brand = new Brand();
        brand.setName(name);
        return brandRepository.save(brand);
    }
}
