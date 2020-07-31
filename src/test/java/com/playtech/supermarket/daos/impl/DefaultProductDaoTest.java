package com.playtech.supermarket.daos.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.money.MonetaryAmount;

import static com.playtech.supermarket.services.impl.DefaultCalculationServiceTest.MILK_PRICE;
import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;
import static com.playtech.supermarket.util.TestUtil.MILK;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductDaoTest {

    @InjectMocks
    DefaultProductDao testObj;

    @Test
    public void getPrice_ForMilk_ShouldReturnPrice(){
        MonetaryAmount actualPrice = testObj.getPrice(MILK);
        MonetaryAmount expectedPrice = createMonetaryAmount(MILK_PRICE);

        assertEquals(expectedPrice, actualPrice);
    }
}
