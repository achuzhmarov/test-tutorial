package com.github.achuzhmarov.tutorial.service.util;

import com.github.achuzhmarov.tutorial.model.Customer;
import com.github.achuzhmarov.tutorial.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class BonusPointCalculator {
    public BigDecimal calculate(Customer customer, List<Product> products, Function<Product, Long> quantities) {
        return products.stream()
            .map(p -> calculatePointsForSingleProduct(customer, p, quantities.apply(p)))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePointsForSingleProduct(Customer customer, Product product, Long quantity) {
        if (product.getDiscount() != null) {
            return BigDecimal.ZERO;
        }

        BigDecimal resultMultiplier = calculateMultipliers(customer, product).stream()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        return product.getPrice()
                .multiply(new BigDecimal(quantity))
                .multiply(resultMultiplier)
                .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
    }

    private List<BigDecimal> calculateMultipliers(Customer customer, Product product) {
        List<BigDecimal> multipliers = new ArrayList<>();

        if (customer.getFavProduct() != null && customer.getFavProduct().equals(product)) {
            if (customer.isPremium()) {
                multipliers.add(new BigDecimal(8));
            } else {
                multipliers.add(new BigDecimal(5));
            }
        } else if (customer.isPremium()) {
            multipliers.add(new BigDecimal(2));
        }

        if (product.isAdvertised()) {
            multipliers.add(new BigDecimal(3));
        }

        if (product.getPrice().compareTo(new BigDecimal(10000)) >= 0) {
            multipliers.add(new BigDecimal(4));
        }

        return multipliers;
    }
}
