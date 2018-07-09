package org.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;

    public Product(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
