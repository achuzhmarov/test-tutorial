package com.github.achuzhmarov.tutorial.user;

import com.github.achuzhmarov.tutorial.common.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerAuthorizationService {
    private final CustomerRepository customerRepository;

    private String currentUserLogin;

    public CustomerAuthorizationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public Customer getCurrentUser() {
        return customerRepository.findByLogin(currentUserLogin)
                .orElseThrow(() -> new DataNotFoundException("User", currentUserLogin));
    }

    public void authorizeUser(String login) {
        currentUserLogin = login;
    }
}
