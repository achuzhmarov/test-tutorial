package com.github.achuzhmarov.tutorial.builder;

import com.github.achuzhmarov.tutorial.product.Product;
import com.github.achuzhmarov.tutorial.user.Customer;

public class UserBuilder {
    Customer user = new Customer();

    public static UserBuilder user() {
        return new UserBuilder()
            .login("login")
            .premium(false);
    }

    public UserBuilder login(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder favProduct(Product product) {
        user.setFavProduct(product);
        return this;
    }

    public UserBuilder premium(Boolean premium) {
        user.setPremium(premium);
        return this;
    }

    public Customer build() {
        return user;
    }
}
