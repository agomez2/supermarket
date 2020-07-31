package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.daos.ProductDao;
import com.playtech.supermarket.daos.impl.DefaultProductDao;
import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Discount;
import com.playtech.supermarket.pojo.Totals;
import com.playtech.supermarket.services.CalculationService;
import com.playtech.supermarket.services.DiscountService;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.util.Map;
import java.util.Set;

import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;

public class DefaultCalculationService implements CalculationService {
    private static final MonetaryAmount zeroPounds = Monetary.getDefaultAmountFactory().setCurrency("GBP")
            .setNumber(0).create();

    DiscountService discountService = new DefaultDiscountService();
    ProductDao productDao = new DefaultProductDao();

    @Override
    public Totals calculateTotals(Basket basket) {
        Totals totals = new Totals();

        MonetaryAmount subtotal = calculateSubtotal(basket);
        Set<Discount> discounts = discountService.applyDiscounts(basket);
        MonetaryAmount totalsDiscounts = calculateTotalsDiscounts(discounts);

        totals.setSubTotal(calculateSubtotal(basket));
        totals.setDiscounts(discounts);
        totals.setTotal(subtotal.add(totalsDiscounts));

        return totals;
    }

    private MonetaryAmount calculateTotalsDiscounts(Set<Discount> discounts) {
        return discounts.stream()
                .map(Discount::getAmount)
                .reduce((MonetaryAmount::add))
                .orElse(zeroPounds);
    }

    private MonetaryAmount calculateSubtotal(Basket basket) {
        MonetaryAmount subTotal = createMonetaryAmount(0);
        for (Map.Entry<String, Integer> entry : basket.getProducts().entrySet()){
            subTotal = subTotal.add(productDao.getPrice(entry.getKey()));
        }
        return subTotal;
    }


}
