package com.github.achuzhmarov.tutorial.model;

import com.github.achuzhmarov.tutorial.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="product")
public class Product extends BaseEntity {
    @Column(name="name", unique = true)
    private String name;

    @Column(name="is_advertised")
    private Boolean isAdvertised;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="discount")
    private BigDecimal discount;

    public Product() {
    }

    /**
     * copy constructor with id ignored
     */
    public Product(Product product) {
        this.setPrice(product.getPrice());
        this.setDiscount(product.getDiscount());
        this.setName(product.getName());
        this.setIsAdvertised(product.isAdvertised());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAdvertised() {
        return isAdvertised;
    }

    public void setIsAdvertised(Boolean isAdvertised) {
        this.isAdvertised = isAdvertised;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
