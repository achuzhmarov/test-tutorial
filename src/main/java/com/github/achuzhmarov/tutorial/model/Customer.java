package com.github.achuzhmarov.tutorial.model;

import com.github.achuzhmarov.tutorial.model.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer extends BaseEntity {
    @Column(name="login", unique = true)
    private String login;

    @ManyToOne
    @JoinColumn(name = "fav_product_id")
    private Product favProduct;

    @Column(name="is_premium")
    private Boolean isPremium;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Product getFavProduct() {
        return favProduct;
    }

    public void setFavProduct(Product favProduct) {
        this.favProduct = favProduct;
    }

    public Boolean isPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }
}