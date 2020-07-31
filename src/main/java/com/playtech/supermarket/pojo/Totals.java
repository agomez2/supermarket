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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Totals totals = (Totals) o;

        if (subTotal != null ? !subTotal.equals(totals.subTotal) : totals.subTotal != null) return false;
        if (discounts != null ? !discounts.equals(totals.discounts) : totals.discounts != null) return false;
        return total != null ? total.equals(totals.total) : totals.total == null;
    }

    @Override
    public int hashCode() {
        int result = subTotal != null ? subTotal.hashCode() : 0;
        result = 31 * result + (discounts != null ? discounts.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Totals{" +
                "subTotal=" + subTotal +
                ", discounts=" + discounts +
                ", total=" + total +
                '}';
    }
}
