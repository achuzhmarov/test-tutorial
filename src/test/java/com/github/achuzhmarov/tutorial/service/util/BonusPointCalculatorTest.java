package com.github.achuzhmarov.tutorial.service.util;

import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.github.achuzhmarov.tutorial.builder.BonusPointsBuilder.bonusPoints;
import static com.github.achuzhmarov.tutorial.builder.CommonBuilder.list;
import static com.github.achuzhmarov.tutorial.builder.CommonBuilder.mapOf;
import static com.github.achuzhmarov.tutorial.builder.CustomerBuilder.customer;
import static com.github.achuzhmarov.tutorial.builder.ProductBuilder.product;
import static com.github.achuzhmarov.tutorial.service.util.BonusPointCalculator.*;
import static org.junit.Assert.assertEquals;

public class BonusPointCalculatorTest {
    private BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

    @Test
    public void calculate_oneProduct() {
        Product product = product("product").price("1.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10").build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_favProduct() {
        Product product = product("product").price("1.00").build();
        Customer customer = customer("customer").favProduct(product).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10").addMultiplier(FAVORITE_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_premiumCustomer() {
        Product product = product("product").price("1.00").build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10").addMultiplier(PREMIUM_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_premiumCustomer_favProduct() {
        Product product = product("product").price("1.00").build();
        Customer customer = customer("customer").favProduct(product).premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10").addMultiplier(PREMIUM_FAVORITE_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_advertisedProduct() {
        Product product = product("product").price("1.00").advertised(true).build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10").addMultiplier(ADVERTISED_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_expensiveProduct() {
        Product product = product("product").price("10000.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("1000.00").addMultiplier(EXPENSIVE_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_notExpensiveProduct_noExpensiveMultiplier() {
        Product product = product("product").price("9999.99").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("1000.00").build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_discountedProduct_noBonusPoints() {
        Product product = product("product").price("1.00").discount("0.50").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        assertEquals(BigDecimal.ZERO, bonus);
    }

    @Test
    public void calculate_twoMultipliers_bothApplied() {
        Product product = product("product").price("1.00").advertised(true).build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("0.10")
                .addMultiplier(PREMIUM_MULTIPLIER)
                .addMultiplier(ADVERTISED_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_threeMultipliers_onlyTwoApplied() {
        Product product = product("product").price("10000.00").advertised(true).build();
        Customer customer = customer("customer").premium(true).build();
        Map<Product, Long> quantities = mapOf(product, 1L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product), quantities::get);

        BigDecimal expectedBonus = bonusPoints("1000.00")
                .addMultiplier(EXPENSIVE_MULTIPLIER)
                .addMultiplier(ADVERTISED_MULTIPLIER).build();
        assertEquals(expectedBonus, bonus);
    }

    @Test
    public void calculate_twoProducts_DifferentQuantity() {
        Product product1 = product("product1").price("1.00").build();
        Product product2 = product("product2").price("10.00").build();
        Customer customer = customer("customer").build();
        Map<Product, Long> quantities = mapOf(product1, 1L, product2, 2L);

        BigDecimal bonus = bonusPointCalculator.calculate(customer, list(product1, product2), quantities::get);

        BigDecimal bonusPointsProduct1 = bonusPoints("0.10").build();
        BigDecimal bonusPointsProduct2 = bonusPoints("1.00").quantity(2).build();
        BigDecimal expectedBonus = bonusPointsProduct1.add(bonusPointsProduct2);
        assertEquals(expectedBonus, bonus);
    }
}
