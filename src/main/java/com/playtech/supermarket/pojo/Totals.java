package com.playtech.supermarket.pojo;

import javax.money.MonetaryAmount;
import java.util.Set;

public class Totals {
    MonetaryAmount subTotal;
    Set<Discount> discounts;
    MonetaryAmount total;

    public MonetaryAmount getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(MonetaryAmount subTotal) {
        this.subTotal = subTotal;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public MonetaryAmount getTotal() {
        return total;
    }

    public void setTotal(MonetaryAmount total) {
        this.total = total;
    }
}
