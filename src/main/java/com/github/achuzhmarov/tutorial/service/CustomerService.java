package com.github.achuzhmarov.tutorial.service;

import com.github.achuzhmarov.tutorial.exception.DataNotFoundException;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.jpa.CustomerRepository;
import com.github.achuzhmarov.tutorial.model.Product;
import com.github.achuzhmarov.tutorial.jpa.ProductRepository;
import com.github.achuzhmarov.tutorial.service.util.BonusPointCalculator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final BonusPointCalculator bonusPointCalculator;

    public CustomerService(CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;

        this.bonusPointCalculator = new BonusPointCalculator();
    }

    @Transactional
    public void setFavProduct(String login, Long productId) {
        Customer customer = customerRepository.findByLogin(login)
                .orElseThrow(() -> new DataNotFoundException("Customer", login));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product", productId));

        customer.setFavProduct(product);
        customerRepository.save(customer);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public BigDecimal calculateBonusPoints(String customerLogin, Map<Long, Long> productQuantities) {
        List<Product> products = productRepository.findAllById(productQuantities.keySet());
        Customer customer = customerRepository.findByLogin(customerLogin)
                .orElseThrow(() -> new DataNotFoundException("Customer", customerLogin));

        return bonusPointCalculator.calculate(customer, products, p -> productQuantities.get(p.getId()));
    }
}