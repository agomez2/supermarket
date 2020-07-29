package com.playtech.supermarket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
        String expectedOutput =
                        "Subtotal:        £3.10\n" +
                        "Apples 10% off:    -10p\n" +
                        "Total:           £3.00\n";

        App.main(new String[]{"Apple", "Milk", "Bread"});
        assertEquals("expectedOutput", outContent.toString());
    }
}
