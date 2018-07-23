package com.github.achuzhmarov.tutorial.controller;

import com.github.achuzhmarov.tutorial.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("{customerLogin}/favorite")
    public void setFavProduct(@PathVariable("customerLogin") String login, @RequestBody Long favProductId) {
        customerService.setFavProduct(login, favProductId);
    }
}