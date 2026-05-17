/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

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