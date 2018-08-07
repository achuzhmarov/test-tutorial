package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.controller.model.CalculateBonusPointsRequest;
import com.github.achuzhmarov.tutorial.model.Product;
import com.github.achuzhmarov.tutorial.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("new")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("{productId}")
    public Product getProduct(@PathVariable("productId") long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("{productId}/edit")
    public void updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
    }
}