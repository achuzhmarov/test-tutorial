package com.github.achuzhmarov.tutorial.builder;

import com.github.achuzhmarov.tutorial.product.Product;

import java.math.BigDecimal;

public class ProductBuilder {
    private Product product = new Product();

    public static ProductBuilder product() {
        return new ProductBuilder()
            .name("product")
            .advertised(false)
            .price("0.00")
            .discount("0.00");
    }

    public ProductBuilder name(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder advertised(Boolean advertised) {
        this.product.setIsAdvertised(advertised);
        return this;
    }

    public ProductBuilder price(String price) {
        this.product.setPrice(new BigDecimal(price));
        return this;
    }

    public ProductBuilder discount(String discount) {
        this.product.setDiscount(new BigDecimal(discount));
        return this;
    }

    public Product build() {
        return product;
    }
}
