package com.github.achuzhmarov.tutorial.product;

import com.github.achuzhmarov.tutorial.BaseIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.achuzhmarov.tutorial.builder.CommonBuilder.number;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static org.junit.Assert.assertEquals;

public class ProductControllerIT extends BaseIT {
    @Autowired
    ProductController productController;

    @Test
    public void createProduct() {
        Product product = product().name("name").price("1.01").discount("0.1").advertised(true).build();

        Product createdProduct = productController.createProduct(product);

        Product dbProduct = productRepository.getOne(createdProduct.getId());
        assertEquals("name", dbProduct.getName());
        assertEquals(number("1.01"), dbProduct.getPrice());
        assertEquals(number("0.1"), dbProduct.getDiscount());
        assertEquals(true, dbProduct.isAdvertised());
    }

    @Test
    public void getProduct() {
        
    }

    @Test
    public void updateProduct() {

    }

    @Test
    public void calculateBonusPoints() {

    }
}
