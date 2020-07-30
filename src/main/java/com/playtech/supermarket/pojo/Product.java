package com.playtech.supermarket.pojo;

import javax.money.MonetaryAmount;

public class Product {
    String id;
    MonetaryAmount price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }
}
