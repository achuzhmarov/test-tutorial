package com.github.achuzhmarov.tutorial.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CustomerController.class,
        CustomerService.class
})
public class CustomerConfiguration {
}
