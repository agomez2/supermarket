package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.playtech.supermarket.util.TestUtil.*;
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

    @Test
    public void addToBasket_BasketHasGotOneAppleAndWeAddOneMore_ShouldEndUpWithTwoApples(){
        Basket basket = getBasketWithApple();

        Basket expectedBasket = createBasketWithProducts(new String[]{APPLE, APPLE});

        Basket actualBasket = testObj.addToBasket(APPLE, basket);

        assertEquals(expectedBasket, actualBasket);
    }

    @Test
    public void addToBasket_BasketHasGotTwoApplesAndWeAddOneOfMilk_ShouldEndUpWithTwoApplesAndOneOfMilk(){
        Basket basket = createBasketWithProducts(new String[]{APPLE, APPLE});

        Basket expectedBasket = createBasketWithProducts(new String[]{APPLE, APPLE, MILK});

        Basket actualBasket = testObj.addToBasket(MILK, basket);

        assertEquals(expectedBasket, actualBasket);
    }
}
