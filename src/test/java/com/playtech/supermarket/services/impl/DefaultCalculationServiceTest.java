package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.daos.ProductDao;
import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Discount;
import com.playtech.supermarket.pojo.Totals;
import com.playtech.supermarket.services.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleDiscounts;
import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleTotals;
import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;
import static com.playtech.supermarket.util.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCalculationServiceTest {

    public static final double MILK_PRICE = 1.30;
    public static final double BREAD_PRICE = 0.8;
    public static final double APPLE_PRICE = 1.0;
    @InjectMocks
    private DefaultCalculationService testObj;

    @Mock
    private DiscountService discountService;

    @Mock
    private ProductDao productDao;

    @Test
    public void calculateTotals_BasketContainsAppleMilkBreadAndApplesAre10PerCentOff_Totals_should_BeOk(){
        Basket basket = getBasketWithAppleMilkBread();
        Totals expectedTotals = createExampleTotals();
        Set<Discount> discounts = createExampleDiscounts();
        when(discountService.applyDiscounts(basket)).thenReturn(discounts);

        when(productDao.getPrice(MILK)).thenReturn(createMonetaryAmount(MILK_PRICE));
        when(productDao.getPrice(BREAD)).thenReturn(createMonetaryAmount(BREAD_PRICE));
        when(productDao.getPrice(APPLE)).thenReturn(createMonetaryAmount(APPLE_PRICE));


        Totals actualTotals = testObj.calculateTotals(basket);

        assertEquals(expectedTotals, actualTotals);
    }
}
