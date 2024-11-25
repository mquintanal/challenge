package com.mql.nexu.cardealership.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mql.nexu.cardealership.models.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByAveragePriceGreaterThan(double price);
    List<Model> findByAveragePriceLessThan(double price);
    List<Model> findByAveragePriceBetween(double lower, double upper);
}
