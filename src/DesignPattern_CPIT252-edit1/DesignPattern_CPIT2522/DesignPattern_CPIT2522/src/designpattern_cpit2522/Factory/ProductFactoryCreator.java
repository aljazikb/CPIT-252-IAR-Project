package designpattern_cpit2522.Factory;

/**
 *
 * @author aljaz
 */
public class ProductFactoryCreator {

    public AddProduct_interface getProduct(String category, String name, String type, double price, int qty) {

        AddProduct_interface factory;

        if (category.equals("Toy")) {
            factory = new ToyFactory();

        } else if (category.equals("Food")) {
            factory = new FoodFactory();

        } else if (category.equals("ElectronicFactory")) {
            factory = new ElectronicFactory();
        } else {
            throw new IllegalArgumentException("Invalid category");
        }

        factory.addProduct(name, type, price, qty);

        return factory;
    }
}
