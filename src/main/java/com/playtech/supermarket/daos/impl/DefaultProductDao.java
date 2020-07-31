package com.playtech.supermarket.daos.impl;

import com.opencsv.CSVReader;
import com.playtech.supermarket.daos.ProductDao;

import javax.money.MonetaryAmount;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.playtech.supermarket.util.MonetaryUtils.createMonetaryAmount;

public class DefaultProductDao implements ProductDao {

    public static final int PRICE_POSITION = 1;
    public static final String PRODUCTS_FILE_NAME = "products.csv";

    //We should use a database here. I implemented it with files for a simplification
    //I would have used docker and add a DB but it would have taken me longer
    @Override
    public MonetaryAmount getPrice(String productName) {
        BigDecimal price = BigDecimal.ZERO;

        Optional<List<String>> record = findRow(productName);
        if (record.isPresent()){
            price = new BigDecimal(record.get().get(PRICE_POSITION));
        }

        return createMonetaryAmount(price);
    }

    private Optional<List<String>> findRow(String productName) {
        String[] values = null;
        InputStream is = getInputStreamForResource(PRODUCTS_FILE_NAME);

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            boolean found = false;
            while (!found && (values = csvReader.readNext()) != null) {
                found = values.length > 0 && productName.equals(values[0]);
            }
        } catch (IOException e) {
            //LOG.error("Could not read price for ...", e)
            e.printStackTrace();
        }
        if (values != null) {
            return Optional.of(Arrays.asList(values));
        } else {
            return Optional.empty();
        }
    }

    private InputStream getInputStreamForResource(String resource) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(resource);
    }
}
