package com.playtech.supermarket;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.pojo.Totals;
import com.playtech.supermarket.services.BasketService;
import com.playtech.supermarket.services.CalculationService;
import com.playtech.supermarket.services.TotalsService;
import com.playtech.supermarket.services.impl.DefaultBasketService;
import com.playtech.supermarket.services.impl.DefaultTotalsService;

/**
 * Hello world!
 *
 */
public class App 
{
    //Ideally we would use Spring or another dependency injection framework
    // but for simplicity we will instantiate directly in the main method
    //@Resource
    private BasketService basketService;

    private TotalsService totalsService;

    private CalculationService calculationService;


    void calculate( String[] args ){
        Basket basket = new Basket();
        for (String item : args) {
            basket = getBasketService().addToBasket(item, basket);
        }
        Totals totals = getCalculationService().calculateTotals(basket);

        //This should be sent to a logger, really.
        System.out.println( totals );
    }


    public static void main( String[] args )
    {
        App app = new App();
        app.setBasketService(new DefaultBasketService());
        app.setTotalService(new DefaultTotalsService());
        app.calculate(args);
    }

    private TotalsService getTotalService() {
        return totalsService;
    }

    private void setTotalService(DefaultTotalsService totalService) {
        this.totalsService = totalService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    public TotalsService getTotalsService() {
        return totalsService;
    }

    public void setTotalsService(TotalsService totalsService) {
        this.totalsService = totalsService;
    }

    public CalculationService getCalculationService() {
        return calculationService;
    }

    public void setCalculationService(CalculationService calculationService) {
        this.calculationService = calculationService;
    }
}
