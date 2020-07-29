package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.services.BasketService;

import java.util.Map;

public class DefaultBasketService implements BasketService {

    @Override
    public Basket addToBasket(String item, Basket basket) {
        Map<String, Integer> products = basket.getProducts();
        Integer quantity = products.get(item);
        if (quantity == null) {
            products.put(item, 1);
        } else {
            products.put(item, quantity + 1);
        }
        basket.setProducts(products);
        return basket;
    }
}
