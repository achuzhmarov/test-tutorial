package com.github.achuzhmarov.tutorial.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BonusPointsBuilder {
    private BigDecimal basePoints;
    private long quantity;
    private List<BigDecimal> multipliers = new ArrayList<>();

    public static BonusPointsBuilder bonusPoints(String basePoints) {
        return new BonusPointsBuilder()
                .basePoints(basePoints)
                .quantity(1L);
    }

    public BonusPointsBuilder basePoints(String basePoints) {
        this.basePoints = new BigDecimal(basePoints);
        return this;
    }

    public BonusPointsBuilder addMultiplier(BigDecimal multiplier) {
        this.multipliers.add(multiplier);
        return this;
    }

    public BonusPointsBuilder quantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal build() {
        return basePoints
            .multiply(new BigDecimal(quantity))
            .multiply(
                multipliers.stream().reduce(BigDecimal.ONE, BigDecimal::multiply)
            );
    }
}
