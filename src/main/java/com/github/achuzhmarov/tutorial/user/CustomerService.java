package com.github.achuzhmarov.tutorial.user;

import com.github.achuzhmarov.tutorial.common.exception.DataNotFoundException;
import com.github.achuzhmarov.tutorial.product.Product;
import com.github.achuzhmarov.tutorial.product.ProductRepository;
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