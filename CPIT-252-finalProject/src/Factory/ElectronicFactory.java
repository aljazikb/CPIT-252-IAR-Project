/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;


import cpit.login;
import Singleton.*;
import Flyweight.*;
import Strategy.*;
import static cpit.login.login;
import java.sql.*;


/**
 *
 * @author noufa
 */
public class ElectronicFactory implements AddProduct_interface {

    //using singleton to connect to the DB
    DBconnectionSingleton singleton = DBconnectionSingleton.getConnectionInstance();
    Connection con = singleton.getConnection();

   
    @Override
    public void addProduct(String name, String specificType, double unitPrice, int quantityInStock) {

        try {

            //add the category to the flyweight
            String category = "Electronic";

            //return the logged in manager ID and store it in the DB --> minimize user data entry
            int managerID = login();

            //create a randomly generated number for the ID --> minimize user data entry
            int inventoryID = (int) (Math.random() * 100000);

            //default discount value is no discount --> changes when user creates a discount from the gui  
            DiscountStrategy strategy = new NoDiscount();
            double finalPrice = strategy.applyDiscount(unitPrice);
            double inventoryValue = finalPrice * quantityInStock;

            //create a flyweight factory to store same values to be generated
            Flyweight sharedData = FlyweightFactory.getSharedData(category, specificType, strategy.getStrategyName());

            String insertion = "INSERT INTO Inventory (ManagerID, InventoryID, Name, Category, SpecificType, UnitPrice, QuantityInStock, InventoryValue, PricingStrategy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insertion);

            ps.setInt(1, managerID);
            ps.setInt(2, inventoryID);
            ps.setString(3, name);
            ps.setString(4, sharedData.getCategory());
            ps.setString(5, sharedData.getSpecificType());
            ps.setDouble(6, finalPrice);
            ps.setInt(7, quantityInStock);
            ps.setDouble(8, inventoryValue);
            ps.setString(9, sharedData.getPricingStrategy());

            ps.executeUpdate();

            System.out.println("Electronic inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}