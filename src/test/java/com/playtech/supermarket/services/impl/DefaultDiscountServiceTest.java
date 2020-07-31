package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Discount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleDiscounts;
import static com.playtech.supermarket.util.TestUtil.createBasketWithAppleMilkBread;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDiscountServiceTest {

    @InjectMocks
    DefaultDiscountService testObj;

    @Test
    public void applyDiscounts_BasketHasAppleMilkAndBreadAndApplesAre10PerCentOff_ShouldApplyApplesDiscount(){
        Set<Discount> expectedDiscounts = createExampleDiscounts();

        Set<Discount> actualDiscounts = testObj.applyDiscounts(createBasketWithAppleMilkBread());

        assertEquals(expectedDiscounts, actualDiscounts);
    }
}
