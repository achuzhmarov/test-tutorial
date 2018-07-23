package com.github.achuzhmarov.tutorial.service;

import com.github.achuzhmarov.tutorial.exception.DataNotFoundException;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.jpa.CustomerRepository;
import com.github.achuzhmarov.tutorial.model.Product;
import com.github.achuzhmarov.tutorial.jpa.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CustomerService(CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
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
}