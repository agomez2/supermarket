package com.playtech.supermarket;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.services.BasketService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest
{
    public static final String APPLE = "Apple";
    public static final String MILK = "Milk";
    public static final String BREAD = "Bread";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    BasketService mockedBasketService;

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

        when(mockedBasketService.printTotals(eq(basketWithAppleMilkBread))).thenReturn(mockedOutput);


        testObj.calculate(input);

        assertEquals(mockedOutput.trim(), outContent.toString().trim());
    }

    private Basket getBasketWithAppleMilkBread() {
        return createBasketWithProducts(new String[]{APPLE, MILK, BREAD});
    }

    private Basket createBasketWithProducts(String[] productNames) {
        Basket basketWithApple = new Basket();
        Map<String, Integer> products = new HashMap<>();
        for (String productName: productNames
             ) {
            Integer quantity = products.get(productName);
            if (quantity == null) {
                products.put(productName, 1);
            } else {
                products.put(productName, quantity + 1);
            }
        }
        basketWithApple.setProducts(products);
        return basketWithApple;
    }

    private Basket getBasketWithAppleMilk() {
        return createBasketWithProducts(new String[]{APPLE, MILK});
    }

    private Basket getBasketWithApple() {
        return createBasketWithProducts(new String[]{APPLE});
    }
}
