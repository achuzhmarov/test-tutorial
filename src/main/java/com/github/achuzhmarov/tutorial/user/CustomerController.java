package com.github.achuzhmarov.tutorial.user;

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