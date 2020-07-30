package com.playtech.supermarket.services;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Discount;

import java.util.Set;

public interface DiscountService {
    Set<Discount> applyDiscounts(Basket basket);
}
