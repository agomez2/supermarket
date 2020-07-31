package com.playtech.supermarket;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.services.BasketService;
import com.playtech.supermarket.services.TotalsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.playtech.supermarket.util.TestUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    BasketService mockedBasketService;

    @Mock
    TotalsService mockedTotalsService;

    @InjectMocks
    App testObj = new App();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Test
    public void testOutputStream()
    {
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @Test
    public void main_BuyAppleMilkBread_ShouldOutputOk()
    {
        String[] input = new String[]{"Apple", "Milk", "Bread"};
        String mockedOutput =
                        "Subtotal:        £3.10\n" +
                        "Apples 10% off:    -10p\n" +
                        "Total:           £3.00\n";

        Basket basketWithApple = getBasketWithApple();
        when(mockedBasketService.addToBasket(eq(APPLE), eq(new Basket()))).thenReturn(basketWithApple);
        Basket basketWithAppleMilk = getBasketWithAppleMilk();
        when(mockedBasketService.addToBasket(eq(MILK), eq(basketWithApple))).thenReturn(basketWithAppleMilk);
        Basket basketWithAppleMilkBread = getBasketWithAppleMilkBread();
        when(mockedBasketService.addToBasket(eq(BREAD), eq(basketWithAppleMilk))).thenReturn(basketWithAppleMilkBread);

        //TODO: Change this to work with totals.
        // I changed the structure of the program and did not have time to fix this one
        //when(mockedTotalsService.printTotals(eq())).thenReturn(mockedOutput);


        testObj.calculate(input);

        assertEquals(mockedOutput.trim(), outContent.toString().trim());
    }

}
