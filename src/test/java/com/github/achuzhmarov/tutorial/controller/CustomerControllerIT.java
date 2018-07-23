package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.controller.common.BaseControllerIT;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.achuzhmarov.tutorial.builder.CustomerBuilder.customer;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static org.junit.Assert.assertEquals;

public class CustomerControllerIT extends BaseControllerIT {
    @Autowired
    CustomerController customerController;

    @Test
    public void setFavProduct_customerFavProductUpdated() {
        Customer customer = customer("customer").premium(false).build();
        Product favProduct = product("favProduct").build();
        customerRepository.save(customer);
        productRepository.save(favProduct);

        customerController.setFavProduct(customer.getLogin(), favProduct.getId());

        Customer dbCustomer = customerRepository.getOne(customer.getId());
        assertEquals(dbCustomer.getFavProduct().getId(), favProduct.getId());
    }
}
