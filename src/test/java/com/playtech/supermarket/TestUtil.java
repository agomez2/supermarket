package com.playtech.supermarket;

import com.playtech.supermarket.pojo.Basket;

import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    public static final String APPLE = "Apple";
    public static final String MILK = "Milk";
    public static final String BREAD = "Bread";

    public static Basket getBasketWithAppleMilkBread() {
        return createBasketWithProducts(new String[]{APPLE, MILK, BREAD});
    }

    public static Basket createBasketWithProducts(String[] productNames) {
        Basket basket = new Basket();
        Map<String, Integer> products = new HashMap<>();
        for (String productName: productNames) {
            Integer quantity = products.get(productName);
            if (quantity == null) {
                products.put(productName, 1);
            } else {
                products.put(productName, quantity + 1);
            }
        }
        basket.setProducts(products);
        return basket;
    }

    public static Basket getBasketWithAppleMilk() {
        return createBasketWithProducts(new String[]{APPLE, MILK});
    }

    public static Basket getBasketWithApple() {
        return createBasketWithProducts(new String[]{APPLE});
    }
}
