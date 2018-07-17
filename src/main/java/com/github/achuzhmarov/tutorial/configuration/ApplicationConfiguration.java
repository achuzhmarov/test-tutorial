package com.github.achuzhmarov.tutorial.configuration;

import com.github.achuzhmarov.tutorial.product.ProductConfiguration;
import com.github.achuzhmarov.tutorial.user.CustomerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CustomerConfiguration.class,
        ProductConfiguration.class,
        PersistenceConfiguration.class
})
public class ApplicationConfiguration {
}
