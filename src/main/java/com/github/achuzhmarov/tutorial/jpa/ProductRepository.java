package com.github.achuzhmarov.tutorial.jpa;

import com.github.achuzhmarov.tutorial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
