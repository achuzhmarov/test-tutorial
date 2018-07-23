package com.github.achuzhmarov.tutorial.service;

import com.github.achuzhmarov.tutorial.exception.DataNotFoundException;
import com.github.achuzhmarov.tutorial.jpa.CustomerRepository;
import com.github.achuzhmarov.tutorial.jpa.ProductRepository;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import com.github.achuzhmarov.tutorial.service.util.BonusPointCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final BonusPointCalculator bonusPointCalculator;

    public ProductService(ProductRepository productRepository,
                          CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.bonusPointCalculator = new BonusPointCalculator();
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

    @Transactional(readOnly = true)
    public BigDecimal calculateBonusPoints(String customerLogin, Map<Long, Long> productQuantities) {
        List<Product> products = productRepository.findAllById(productQuantities.keySet());
        Customer customer = customerRepository.findByLogin(customerLogin)
                .orElseThrow(() -> new DataNotFoundException("Customer", customerLogin));

        return bonusPointCalculator.calculate(customer, products, p -> productQuantities.get(p.getId()));
    }
}