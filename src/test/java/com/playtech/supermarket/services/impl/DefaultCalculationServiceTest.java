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

import java.math.BigDecimal;
import java.util.Set;

import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleDiscounts;
import static com.playtech.supermarket.services.impl.DefaultTotalsServiceTest.createExampleTotals;
import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;
import static com.playtech.supermarket.util.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCalculationServiceTest {

    public static final BigDecimal MILK_PRICE = BigDecimal.valueOf(1.30);
    public static final BigDecimal BREAD_PRICE = BigDecimal.valueOf(0.8);
    public static final BigDecimal APPLE_PRICE = BigDecimal.valueOf(1.0);
    @InjectMocks
    private DefaultCalculationService testObj;

    @Mock
    private DiscountService discountService;

    @Mock
    private ProductDao productDao;

    @Test
    //This is not really a unit test because it is not mocking the file reading
    //This should go into an integration test and in this file we should add a test like the one
    // below but mocking all the file functionality so only the dao is tested
    public void calculateTotals_IntegrationTestBasketContainsAppleMilkBreadAndApplesAre10PerCentOff_Totals_should_BeOk(){
        Basket basket = createBasketWithAppleMilkBread();
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
