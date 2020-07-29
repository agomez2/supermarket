package com.playtech.supermarket.services;


import com.playtech.supermarket.pojo.Basket;

public interface BasketService {

    String printTotals(Basket basket);

    Basket addToBasket(String item, Basket basket);
}
