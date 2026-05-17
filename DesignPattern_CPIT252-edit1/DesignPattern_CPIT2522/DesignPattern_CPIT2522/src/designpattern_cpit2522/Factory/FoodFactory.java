/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package designpattern_cpit2522.Factory;

import designpattern_cpit2522.DB_Singleton.*;
import designpattern_cpit2522.Flyweight.FlyweightFactory;
import designpattern_cpit2522.Flyweight.SharedProductFlyweight;
import designpattern_cpit2522.Strategy.*;
import designpattern_cpit2522.login;
import java.sql.*;

/**
 *
 * @author noufa
 */
public class FoodFactory implements AddProduct_interface {

    //using singleton to connect to the DB
    DBconnectionSingleton singleton = DBconnectionSingleton.getConnectionInstance();
    Connection con = singleton.getConnection();

    @Override
    public void addProduct(String name, String specificType, double unitPrice, int quantityInStock) {

        try {

            //add the category to the flyweight
            String category = "Food";

            //return the logged in manager ID and store it in the DB --> minimize user data entry
            int managerID = login.login();

            //create a randomly generated number for the ID --> minimize user data entry
            int inventoryID = (int) (Math.random() * 100000);

            //default discount value is no discount --> changes when user creates a discount from the gui  
            DiscountStrategy strategy = new NoDiscount();
            double finalPrice = strategy.applyDiscount(unitPrice);
            double inventoryValue = finalPrice * quantityInStock;

            //create a flyweight factory to store same values to be generated
            SharedProductFlyweight sharedData = FlyweightFactory.getSharedData(category, specificType, strategy.getStrategyName());

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

            System.out.println("Food inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
