package com.playtech.supermarket.services;


import com.playtech.supermarket.pojo.Basket;

public interface BasketService {

    Basket addToBasket(String item, Basket basket);
}
