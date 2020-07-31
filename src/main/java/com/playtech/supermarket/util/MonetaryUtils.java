package com.playtech.supermarket.util;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public class MonetaryUtils {

    public static MonetaryAmount createMonetaryAmount(BigDecimal howMuch){
        return Monetary.getDefaultAmountFactory().setCurrency("GBP")
                .setNumber(howMuch).create();
    }
}
