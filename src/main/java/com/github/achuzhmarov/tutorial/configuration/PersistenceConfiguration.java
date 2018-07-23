package com.github.achuzhmarov.tutorial.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.github.achuzhmarov.tutorial.jpa"
)
@EnableTransactionManagement
public class PersistenceConfiguration {

}
