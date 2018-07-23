package com.github.achuzhmarov.tutorial.jpa;

import com.github.achuzhmarov.tutorial.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByLogin(String login);
}
