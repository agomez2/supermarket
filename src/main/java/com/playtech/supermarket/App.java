package com.playtech.supermarket;

import com.playtech.supermarket.pojo.Basket;
import com.playtech.supermarket.services.BasketService;
import com.playtech.supermarket.services.impl.DefaultBasketService;

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


    void calculate( String[] args ){
        Basket basket = new Basket();
        for (String item : args) {
            basket = getBasketService().addToBasket(item, basket);
        }

        System.out.println( getBasketService().printTotals(basket) );
    }

    public static void main( String[] args )
    {
        App app = new App();
        app.setBasketService(new DefaultBasketService());
        app.calculate(args);
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }
}
