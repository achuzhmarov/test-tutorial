package com.github.achuzhmarov.tutorial.service.util;

import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.github.achuzhmarov.tutorial.builder.CommonBuilder.*;
import static com.github.achuzhmarov.tutorial.builder.CustomerBuilder.customer;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.productWithId;
import static org.junit.Assert.assertEquals;

public class BonusPointCalculatorTest {
    private BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

    @Test
    public void calculate_oneProduct() {
        Product product = productWithId("product").price("1.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.10"), bonus);
    }

    @Test
    public void calculate_favProduct() {
        Product product = productWithId("product").price("1.00").build();
        Customer customer = customer("customer").build();
        customer.setFavProduct(product);
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.50"), bonus);
    }

    @Test
    public void calculate_premiumCustomer() {
        Product product = productWithId("product").price("1.00").build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.20"), bonus);
    }

    @Test
    public void calculate_premiumCustomer_favProduct() {
        Product product = productWithId("product").price("1.00").build();
        Customer customer = customer("customer").premium(true).build();
        customer.setFavProduct(product);
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.80"), bonus);
    }

    @Test
    public void calculate_advertisedProduct() {
        Product product = productWithId("product").price("1.00").advertised(true).build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.30"), bonus);
    }

    @Test
    public void calculate_expensiveProduct() {
        Product product = productWithId("product").price("10000.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("4000.00"), bonus);
    }

    @Test
    public void calculate_notExpensiveProduct_noExpensiveMultiplier() {
        Product product = productWithId("product").price("9999.99").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("1000.00"), bonus);
    }

    @Test
    public void calculate_discountedProduct_noBonusPoints() {
        Product product = productWithId("product").price("1.00").discount("0.50").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(BigDecimal.ZERO, bonus);
    }

    @Test
    public void calculate_twoMultipliers_bothApplied() {
        Product product = productWithId("product").price("1.00").advertised(true).build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("0.60"), bonus);
    }

    @Test
    public void calculate_threeMultipliers_onlyTwoApplied() {
        Product product = productWithId("product").price("10000.00").advertised(true).build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(number("12000.00"), bonus);
    }

    @Test
    public void calculate_twoProducts() {
        Product product1 = productWithId("product1").price("1.00").build();
        Product product2 = productWithId("product2").price("10.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product1, 1L, product2, 2L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product1, product2), quantities::get);

        assertEquals(number("2.10"), bonus);
    }
}
