package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.playtech.supermarket.util.TestUtil.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTotalServiceTest {

    @InjectMocks
    DefaultTotalService testObj;

    @Test
    public void printTotals_BasketHasAppleMilkAndBreadAndThereIsTenPerCentDiscountInApples_ItShouldOutputOk(){
        String expectedOutput =
                        "Subtotal:        £3.10\n" +
                        "Apples 10% off:    -10p\n" +
                        "Total:           £3.00\n";

        Basket basket = createBasketWithProducts(new String[]{APPLE, MILK, BREAD});
        String actualOutput = testObj.printTotals(basket);

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
