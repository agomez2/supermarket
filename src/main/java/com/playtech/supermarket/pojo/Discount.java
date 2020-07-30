package com.playtech.supermarket.pojo;

import javax.money.MonetaryAmount;

public class Discount {
    String description;
    MonetaryAmount amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }
}
