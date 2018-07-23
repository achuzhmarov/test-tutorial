package com.github.achuzhmarov.tutorial.product;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

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
    public Product updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @PostMapping("buy")
    public BigDecimal calculateBonusPoints(@RequestBody CalculateBonusPointsRequest request) {
        return productService.calculateBonusPoints(request.getCustomerLogin(), request.getProductQuantities());
    }
}