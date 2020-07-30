package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Totals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleTotals;
import static com.playtech.supermarket.util.TestUtil.getBasketWithAppleMilkBread;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCalculationServiceTest {

    @InjectMocks
    DefaultCalculationService testObj;

    @Test
    public void calculateTotals_BasketContainsAppleMilkBreadAndApplesAre10PerCentOff_Totals_should_BeOk(){
        Basket basket = getBasketWithAppleMilkBread();

        Totals expectedTotals = createExampleTotals();

        Totals actualTotals = testObj.calculateTotals(basket);

        assertEquals(actualTotals, expectedTotals);
    }
}
