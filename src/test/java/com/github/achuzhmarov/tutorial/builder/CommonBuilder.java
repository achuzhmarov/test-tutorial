package com.github.achuzhmarov.tutorial.builder;

import java.math.BigDecimal;

public class CommonBuilder {
    public static BigDecimal number(String number) {
        return new BigDecimal(number);
    }
}
