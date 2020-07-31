package com.playtech.supermarket.pojo;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    Map<String, Integer> products;

    public Basket(){
        products = new HashMap<>();
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return products != null ? products.equals(basket.products) : basket.products == null;
    }

    @Override
    public int hashCode() {
        return products != null ? products.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products +
                '}';
    }
}
