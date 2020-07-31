package com.playtech.supermarket.util;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class MonetaryUtils {

    public static MonetaryAmount createMonetaryAmount(double howMuch){
        return Monetary.getDefaultAmountFactory().setCurrency("GBP")
                .setNumber(howMuch).create();
    }
}
