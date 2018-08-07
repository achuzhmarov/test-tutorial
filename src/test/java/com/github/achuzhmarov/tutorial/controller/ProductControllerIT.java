package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.controller.common.BaseControllerIT;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.achuzhmarov.tutorial.builder.CommonBuilder.number;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static org.junit.Assert.assertEquals;

public class ProductControllerIT extends BaseControllerIT {
    @Autowired
    ProductController productController;

    @Test
    public void createProduct_productSaved() {
        Product product = product("productName").price("1.01").discount("0.1").advertised(true).build();

        Product createdProduct = productController.createProduct(product);

        Product dbProduct = productRepository.getOne(createdProduct.getId());
        assertEquals("productName", dbProduct.getName());
        assertEquals(number("1.01"), dbProduct.getPrice());
        assertEquals(number("0.1"), dbProduct.getDiscount());
        assertEquals(true, dbProduct.isAdvertised());
    }

    @Test
    public void getProduct_oneProductInDb_productReturned() {
        Product product = product("productName").build();
        productRepository.save(product);

        Product result = productController.getProduct(product.getId());

        assertEquals("productName", result.getName());
    }

    @Test
    public void getProduct_twoProductsInDb_correctProductReturned() {
        Product product1 = product("product1").build();
        Product product2 = product("product2").build();
        productRepository.save(product1);
        productRepository.save(product2);

        Product result = productController.getProduct(product1.getId());

        assertEquals("product1", result.getName());
    }

    @Test
    public void updateProduct_productUpdated() {
        Product product = product("productName").build();
        productRepository.save(product);

        Product updatedProduct = product("updatedName").price("1.1").discount("0.5").advertised(true).build();
        updatedProduct.setId(product.getId());


        productController.updateProduct(product.getId(), updatedProduct);


        Product dbProduct = productRepository.getOne(product.getId());
        assertEquals("updatedName", dbProduct.getName());
        assertEquals(number("1.1"), dbProduct.getPrice());
        assertEquals(number("0.5"), dbProduct.getDiscount());
        assertEquals(true, dbProduct.isAdvertised());
    }
}