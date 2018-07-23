package com.github.achuzhmarov.tutorial.controller.model;

import java.util.Map;

public class CalculateBonusPointsRequest {
    private final String customerLogin;
    private final Map<Long, Long> productQuantities;

    public CalculateBonusPointsRequest(String customerLogin, Map<Long, Long> productQuantities) {
        this.customerLogin = customerLogin;
        this.productQuantities = productQuantities;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public Map<Long, Long> getProductQuantities() {
        return productQuantities;
    }
}
