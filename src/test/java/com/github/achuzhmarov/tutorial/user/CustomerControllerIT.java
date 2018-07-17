package com.github.achuzhmarov.tutorial.user;

import com.github.achuzhmarov.tutorial.BaseIT;
import com.github.achuzhmarov.tutorial.product.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static com.github.achuzhmarov.tutorial.builder.UserBuilder.user;
import static org.junit.Assert.assertEquals;

public class CustomerControllerIT extends BaseIT {
    @Autowired
    CustomerController customerController;

    @Test
    public void setFavProduct() {
        Customer user = user().login("user").premium(false).build();
        Product favProduct = product().name("userProduct").build();
        customerRepository.save(user);
        productRepository.save(favProduct);
        authorizeUser(user);

        customerController.setFavProduct(favProduct.getId());

        Customer dbUser = customerRepository.getOne(user.getId());
        assertEquals(dbUser.getFavProduct().getId(), favProduct.getId());
    }
}
