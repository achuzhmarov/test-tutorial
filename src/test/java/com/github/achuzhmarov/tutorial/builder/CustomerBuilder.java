package com.github.achuzhmarov.tutorial.builder;

import com.github.achuzhmarov.tutorial.model.Product;
import com.github.achuzhmarov.tutorial.model.Customer;

public class CustomerBuilder {
    Customer customer = new Customer();

    public static CustomerBuilder customer() {
        return new CustomerBuilder()
            .login("login")
            .premium(false);
    }

    public CustomerBuilder login(String login) {
        customer.setLogin(login);
        return this;
    }

    public CustomerBuilder favProduct(Product product) {
        customer.setFavProduct(product);
        return this;
    }

    public CustomerBuilder premium(Boolean premium) {
        customer.setPremium(premium);
        return this;
    }

    public Customer build() {
        return customer;
    }
}
