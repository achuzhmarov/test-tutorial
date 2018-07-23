package com.github.achuzhmarov.tutorial.controller.model;

import java.util.Map;

public class CalculateBonusPointsRequest {
    private String customerLogin;
    private Map<Long, Long> productQuantities;

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public Map<Long, Long> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Long, Long> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
