package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.BaseIT;
import com.github.achuzhmarov.tutorial.controller.CustomerController;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static com.github.achuzhmarov.tutorial.builder.CustomerBuilder.customer;
import static org.junit.Assert.assertEquals;

public class CustomerControllerIT extends BaseIT {
    @Autowired
    CustomerController customerController;

    @Test
    public void setFavProduct() {
        Customer customer = customer().login("customer").premium(false).build();
        Product favProduct = product().name("userProduct").build();
        customerRepository.save(customer);
        productRepository.save(favProduct);

        customerController.setFavProduct(customer.getLogin(), favProduct.getId());

        Customer dbCustomer = customerRepository.getOne(customer.getId());
        assertEquals(dbCustomer.getFavProduct().getId(), favProduct.getId());
    }
}
