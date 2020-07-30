package com.playtech.supermarket.daos;

import javax.money.MonetaryAmount;

public interface ProductDao {
    public MonetaryAmount getPrice(String productName);
}
