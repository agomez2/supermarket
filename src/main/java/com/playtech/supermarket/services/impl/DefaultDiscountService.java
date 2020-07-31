package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Discount;
import com.playtech.supermarket.services.DiscountService;

import java.util.HashSet;
import java.util.Set;

public class DefaultDiscountService implements DiscountService {
    @Override
    public Set<Discount> applyDiscounts(Basket basket) {
        return new HashSet<>();
    }
}
