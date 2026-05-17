
import Flyweight.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author noufa
 */
public class FlyweightTest {
 
    // tests if the Flyweight Factory reuses existing Flyweight object when the same shared data is requested
    @Test
    public void testFlyweightReuse() {
        Flyweight fly1 = FlyweightFactory.getSharedData("Food", "Chips", "No Discount");
        Flyweight fly2 = FlyweightFactory.getSharedData("Food", "Chips", "No Discount");

        assertSame(fly1, fly2);
    }

    // tests if different shared data creates different Flyweight objects
    @Test
    public void testDiffKeysDiffFlyweights() {
        Flyweight fly1 = FlyweightFactory.getSharedData("Food", "Chips", "No Discount");
        Flyweight fly2 = FlyweightFactory.getSharedData("Food", "Chips", "Seasonal Discount");

        assertNotSame(fly1, fly2);
    }

    // tests whether the Flyweight object correctly stores and returns values
    @Test
    public void testFlyweightStoresCorrectData() {
        Flyweight fly = FlyweightFactory.getSharedData("Toy", "Robot", "75% Discount");

        assertEquals("Toy", fly.getCategory());
        assertEquals("Robot", fly.getSpecificType());
        assertEquals("75% Discount", fly.getPricingStrategy());
    }

    // tests whether the factory method returns an object of type Flyweight
    @Test
    public void testFactoryReturnsInterface() {
        Flyweight fly = FlyweightFactory.getSharedData("Electronics", "Speaker", "Seasonal Discount");
        assertTrue(fly instanceof Flyweight);
    }
}
   



