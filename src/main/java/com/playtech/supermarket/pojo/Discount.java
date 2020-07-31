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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (description != null ? !description.equals(discount.description) : discount.description != null)
            return false;
        return amount != null ? amount.equals(discount.amount) : discount.amount == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
