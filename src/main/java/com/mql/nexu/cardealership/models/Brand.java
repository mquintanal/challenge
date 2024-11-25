package com.mql.nexu.cardealership.models;

import java.util.List;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Brand {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    public double getAveragePrice() {
        return models.stream()
                     .mapToDouble(Model::getAveragePrice)
                     .average()
                     .orElse(0.0);
    }

   
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}
}
