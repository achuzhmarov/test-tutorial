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

    private final CustomerAuthorizationService customerAuthorizationService;

    public CustomerService(CustomerRepository customerRepository,
                           ProductRepository productRepository,
                           CustomerAuthorizationService customerAuthorizationService) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerAuthorizationService = customerAuthorizationService;
    }

    @Transactional
    public void setFavProduct(Long productId) {
        Customer currentCustomer = customerAuthorizationService.getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product", productId));

        currentCustomer.setFavProduct(product);
        customerRepository.save(currentCustomer);
    }
}
