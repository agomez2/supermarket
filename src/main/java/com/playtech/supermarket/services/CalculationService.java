package com.playtech.supermarket.services;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Totals;

public interface CalculationService {
    Totals calculateTotals(Basket basket);
}
