package com.github.achuzhmarov.tutorial.product;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    ProductController.class,
    ProductService.class
})
public class ProductConfiguration {
}
