package com.github.achuzhmarov.tutorial.builder;

import com.github.achuzhmarov.tutorial.model.Product;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class ProductBuilder {
    private Product product = new Product();

    private static AtomicLong idGenerator = new AtomicLong();

    public static ProductBuilder product(String name) {
        return new ProductBuilder()
            .name(name)
            .advertised(false)
            .price("0.00");
    }

    public ProductBuilder id(Long id) {
        this.product.setId(id);
        return this;
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
