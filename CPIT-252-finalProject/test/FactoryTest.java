
import Factory.*;
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
public class FactoryTest {
    // both food and electronics factories are the same code, just different names --> tests are the same
    @Test
    public void testFactoryToyProduct() {
        ProductFactoryCreator creator = new ProductFactoryCreator();
        AddProduct_interface factory = creator.getProduct("Toy", "Barbie", "Plastic", 65.8, 70);

        assertNotNull(factory);
        assertTrue(factory instanceof ToyFactory);
    }

    // tests false categories and sees how the system handles it
    // categories allowed are the factories available --> toy, food, and electronics
    // therefore False is illegal and will throw an IllegalArgumentException
    @Test
    public void testInvalidCategory() {
        ProductFactoryCreator creator = new ProductFactoryCreator();

        assertThrows(IllegalArgumentException.class, () -> {
            creator.getProduct("False", "name", "type", 17.0, 300);
        });
    }
}
