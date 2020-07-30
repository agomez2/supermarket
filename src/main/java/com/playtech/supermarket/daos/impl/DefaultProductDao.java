package com.playtech.supermarket.daos.impl;

import com.playtech.supermarket.daos.ProductDao;

import javax.money.MonetaryAmount;

import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;

public class DefaultProductDao implements ProductDao {

    @Override
    public MonetaryAmount getPrice(String productName) {
        
        return createMonetaryAmount(price);
    }
}
