package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Discount;
import com.playtech.supermarket.pojo.Totals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.money.Monetary;
import javax.money.MonetaryAmountFactory;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTotalsServiceTest {

    public static final String GBP = "GBP";
    private static final MonetaryAmountFactory<?> defaultAmountFactory = Monetary.getDefaultAmountFactory();

    @InjectMocks
    DefaultTotalsService testObj;

    @Test
    public void printTotals_TotalsHasAppleMilkAndBreadAndThereIsTenPerCentDiscountInApples_ItShouldOutputOk(){
        String expectedOutput =
                        "Subtotal:              £3.10\n" +
                        "Apples 10% off:          -10p\n" +
                        "Total:                 £3.00\n";
        Totals totals = createExampleTotals();
        String actualOutput = testObj.printTotals(totals);

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    public static Totals createExampleTotals() {
        Totals totals = new Totals();

        totals.setSubTotal(defaultAmountFactory
                .setCurrency(GBP)
                .setNumber(3.10)
                .create()
        );

        totals.setDiscounts(
                createExampleDiscounts()
        );

        totals.setTotal(defaultAmountFactory
                .setCurrency(GBP)
                .setNumber(3L)
                .create()
        );

        return totals;
    }

    //TODO: Move this to a Util class
    public static Set<Discount> createExampleDiscounts() {
        Set<Discount> discounts = new HashSet<>();
        Discount discount = createExampleDiscount();
        discounts.add(discount);
        return discounts;
    }

    private static Discount createExampleDiscount() {
        Discount discount = new Discount();
        discount.setAmount(defaultAmountFactory
                .setCurrency(GBP)
                .setNumber(-0.1)
                .create()
        );
        discount.setDescription("Apples 10% off:");
        return discount;
    }
}
