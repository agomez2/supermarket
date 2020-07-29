package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.playtech.supermarket.TestUtil.APPLE;
import static com.playtech.supermarket.TestUtil.getBasketWithApple;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBasketServiceTest {

    @InjectMocks
    DefaultBasketService testObj;

    @Test
    public void addToBasket_BasketIsEmptyAndWeAddOneProduct_ShouldEndUpWithOneProduct(){
        Basket basket = new Basket();

        Basket expectedBasket = getBasketWithApple();

        Basket actualBasket = testObj.addToBasket(APPLE, basket);

        assertEquals(expectedBasket, actualBasket);
    }
}
