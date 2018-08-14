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
    static final BigDecimal BONUS_POINTS_BASE_PERCENT = BigDecimal.TEN;
    static final int MULTIPLIERS_LIMIT = 2;
    static final BigDecimal PREMIUM_MULTIPLIER = new BigDecimal(2);
    static final BigDecimal FAVORITE_MULTIPLIER = new BigDecimal(5);
    static final BigDecimal PREMIUM_FAVORITE_MULTIPLIER = new BigDecimal(8);
    static final BigDecimal ADVERTISED_MULTIPLIER = new BigDecimal(3);
    static final BigDecimal EXPENSIVE_MULTIPLIER = new BigDecimal(4);
    static final BigDecimal EXPENSIVE_THRESHOLD = new BigDecimal(10000);

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
                .limit(MULTIPLIERS_LIMIT)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        return product.getPrice()
                .multiply(new BigDecimal(quantity))
                .multiply(resultMultiplier)
                .divide(BONUS_POINTS_BASE_PERCENT, RoundingMode.HALF_UP);
    }

    private List<BigDecimal> calculateMultipliers(Customer customer, Product product) {
        List<BigDecimal> multipliers = new ArrayList<>();

        if (customer.getFavProduct() != null && customer.getFavProduct().equals(product)) {
            if (customer.isPremium()) {
                multipliers.add(PREMIUM_FAVORITE_MULTIPLIER);
            } else {
                multipliers.add(FAVORITE_MULTIPLIER);
            }
        } else if (customer.isPremium()) {
            multipliers.add(PREMIUM_MULTIPLIER);
        }

        if (product.isAdvertised()) {
            multipliers.add(ADVERTISED_MULTIPLIER);
        }

        if (product.getPrice().compareTo(EXPENSIVE_THRESHOLD) >= 0) {
            multipliers.add(EXPENSIVE_MULTIPLIER);
        }

        return multipliers;
    }
}
