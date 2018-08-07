package com.github.achuzhmarov.tutorial.service;

import com.github.achuzhmarov.tutorial.exception.DataNotFoundException;
import com.github.achuzhmarov.tutorial.jpa.ProductRepository;
import com.github.achuzhmarov.tutorial.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new DataNotFoundException("Product", productId));
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(new Product(product));
    }

    @Transactional
    public Product updateProduct(Long productId, Product product) {
        Product dbProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product", productId));

        dbProduct.setPrice(product.getPrice());
        dbProduct.setDiscount(product.getDiscount());
        dbProduct.setName(product.getName());
        dbProduct.setIsAdvertised(product.isAdvertised());

        return productRepository.save(dbProduct);
    }
}