package com.github.achuzhmarov.product;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/new")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/product/{productId}/edit")
    public Product updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @PostMapping("/products/buy")
    public BigDecimal calculateBonusPoints(@RequestBody Map<Long, Long> productQuantities) {
        return productService.calculateBonusPoints(productQuantities);
    }
}
