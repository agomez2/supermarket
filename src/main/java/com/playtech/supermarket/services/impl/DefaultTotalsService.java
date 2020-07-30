package com.playtech.supermarket.services.impl;

import com.playtech.supermarket.pojo.Discount;
import com.playtech.supermarket.pojo.Totals;
import com.playtech.supermarket.services.TotalsService;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

public class DefaultTotalsService implements TotalsService {

    //Ideally, move this to a configuration property so it is changeable on the fly
    public static final int AVAILABLE_CHARS_PER_LINE = 28;
    public static final String TOTAL = "Total:";
    public static final String SUBTOTAL = "Subtotal:";
    private static final MonetaryAmount onePound = Monetary.getDefaultAmountFactory().setCurrency("GBP")
      .setNumber(1).create();
    public static final String PENNY_SYMBOL = "p";

    //This should depend on the region
    MonetaryAmountFormat penniesFormatGBP = getPenniesFormat();
    MonetaryAmountFormat poundsFormatGBP = getPoundsFormat();

    private MonetaryAmountFormat getPenniesFormat() {
        return MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.
                of(Locale.UK).set(CurrencyStyle.SYMBOL).set("pattern", "00").build());
    }

    private MonetaryAmountFormat getPoundsFormat() {
        return MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.
                of(Locale.UK).set(CurrencyStyle.SYMBOL).set("pattern", "¤0.00").build());
    }

    @Override
    public String printTotals(Totals totals) {
        String subTotal = printSubTotal(totals);
        String discounts = printDiscounts(totals);
        String total = printTotal(totals);

        return subTotal + discounts + total;
    }

    //TODO: This method and the one below have duplicated code. Refactor
    private String printSubTotal(Totals totals) {
        MonetaryAmount amount = totals.getSubTotal();
        String ukFormatted = formatMonetaryAmount(amount);
        return printLine(SUBTOTAL, ukFormatted, amount.isLessThan(onePound));
    }

    private String printTotal(Totals totals) {
        MonetaryAmount amount = totals.getTotal();
        String ukFormatted = formatMonetaryAmount(amount);
        return printLine(TOTAL, ukFormatted, amount.isLessThan(onePound));
    }

    private String formatMonetaryAmount(MonetaryAmount amount) {
        if (amount.isLessThan(onePound)){
            MonetaryAmount penniesAmount = Monetary.getDefaultAmountFactory().setCurrency("GBP")
                    .setNumber(amount.multiply(100).getNumber()).create();
            return penniesFormatGBP.format(penniesAmount) + PENNY_SYMBOL;
        } else {
            return poundsFormatGBP.format(amount);
        }
    }


    private String printLine(String label, String formattedMonetaryAmount, boolean allowOneMoreChar) {
        int availableChars = AVAILABLE_CHARS_PER_LINE + (allowOneMoreChar ? 1:0);
        availableChars -= label.length();
        availableChars -= formattedMonetaryAmount.length();

        //Handle here labels that are too long to fit

        return label + printSpaces(availableChars) + formattedMonetaryAmount + "\n";
    }

    private String printSpaces(int howMany) {
        return " ".repeat(howMany);
    }

    private String printDiscounts(Totals totals) {
        StringBuilder printedDiscounts = new StringBuilder();
        for (Discount discount : totals.getDiscounts()) {
            MonetaryAmount amount = discount.getAmount();
            String formattedAmount = formatMonetaryAmount(amount);
            printedDiscounts.append(printLine(discount.getDescription(), formattedAmount, amount.isLessThan(onePound)));
        }
        return printedDiscounts.toString();
    }
}