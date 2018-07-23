package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.controller.common.BaseServerIT;
import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;

import static com.github.achuzhmarov.tutorial.builder.CustomerBuilder.customer;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static org.junit.Assert.assertEquals;

public class CustomerControllerServerIT extends BaseServerIT {
    @Test
    public void setFavProduct_customerFavProductUpdated() {
        Customer customer = customer("customerLogin").premium(false).build();
        Product favProduct = product("favProduct").build();

        runInTransaction(() -> {
            customerRepository.save(customer);
            productRepository.save(favProduct);
        });

        restTemplate.postForObject("/customer/customerLogin/favorite", favProduct.getId(), Void.class);

        runInTransaction(() -> {
            Customer dbCustomer = customerRepository.getOne(customer.getId());
            assertEquals(favProduct.getId(), dbCustomer.getFavProduct().getId());
        });
    }
}
