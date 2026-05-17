
import Strategy.*;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noufa
 */
public class StrategyTest {
    
    //// testing seasonal discounts ////
    // ask for seasonal discount (equals 80%) --> we get 100 * 0.8 = 80.0
    @Test
    public void testSeasonalDiscount() {
        SeasonalDiscount sd = new SeasonalDiscount();
        assertEquals(80.0, sd.applyDiscount(100), 0.0);
    }

    // ask for "Seasonal Discount" strategy recognition --> we get the percentage strategy name output
    @Test
    public void testSeasonalName() {
        SeasonalDiscount sd = new SeasonalDiscount();
        assertEquals("Seasonal Discount", sd.getStrategyName());
    }

    //// testing percentage discounts ////
    // ask for 50% off 200 --> we get 100 as the price
    @Test
    public void testPercentageDiscount() {
        PercentageDiscount pd = new PercentageDiscount(50);
        assertEquals(100.0, pd.applyDiscount(200), 0.0);
    }

    // ask for "80.0% Discount" strategy recognition --> name output
    @Test
    public void testPercentageName() {
        PercentageDiscount pd = new PercentageDiscount(80);
        assertEquals("80.0% Discount", pd.getStrategyName());
    }

    //// testing no discount ////
    @Test
    public void testNoDiscount() {
        NoDiscount nd = new NoDiscount();
        assertEquals(95.6, nd.applyDiscount(95.6), 0.0);
    }

    // ask for "No Discount" strategy recognition
    @Test
    public void testNoDiscountName() {
        NoDiscount nd = new NoDiscount();
        assertEquals("No Discount", nd.getStrategyName());
    }

    //// testing context ////
    /* test context by:
       1- create context object and select the strategy type (Percentage discount in this test)
       2- call the executeDiscount method which does: ask for 50% off 100 --> we get 50 as the price
       3- lastly, it asserts equals.
     */
    @Test
    public void testExecuteDiscount() {
        Context ctx = new Context(new PercentageDiscount(50));
        assertEquals(50.0, ctx.executeDiscount(100), 0.0);
    }

    // ask for "Seasonal Discount" strategy recognition via the context class
    @Test
    public void testGetStrategyExecuteDiscount() {
        Context ctx = new Context(new SeasonalDiscount());
        assertEquals("Seasonal Discount", ctx.getStrategyName());
    }
}

