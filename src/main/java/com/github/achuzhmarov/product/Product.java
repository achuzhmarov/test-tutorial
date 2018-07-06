package com.github.achuzhmarov.product;

import com.github.achuzhmarov.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="product")
public class Product extends BaseEntity {
    @Column(name="name")
    private String name;

    @Column(name="is_new")
    private Boolean isNew;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="discount")
    private BigDecimal discount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
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
