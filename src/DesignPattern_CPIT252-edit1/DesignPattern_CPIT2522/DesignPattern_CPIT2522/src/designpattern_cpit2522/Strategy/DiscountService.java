/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern_cpit2522.Strategy;

import designpattern_cpit2522.DB_Singleton.DBconnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aljaz
 */
public class DiscountService {

    private final DBconnectionSingleton db = DBconnectionSingleton.getConnectionInstance();

    
    public void applyDiscount(int inventoryID, DiscountStrategy strategy) throws Exception {

        try (Connection con = db.getConnection()) {

            String query = "SELECT UnitPrice, QuantityInStock FROM Inventory WHERE InventoryID = ?";
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setInt(1, inventoryID);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                throw new IllegalArgumentException("Product with ID " + inventoryID + " not found.");
            }

            double oldPrice = rs.getDouble("UnitPrice");
            int quantity = rs.getInt("QuantityInStock");

            // Context is the only one that touches the strategy
            Context context = new Context(strategy);
            double newPrice = context.executeDiscount(oldPrice);
            double inventoryValue = newPrice * quantity;

            String update = "UPDATE Inventory "+ "SET UnitPrice=?, InventoryValue=?, PricingStrategy=? "+ "WHERE InventoryID=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setDouble(1, newPrice);
            ps2.setDouble(2, inventoryValue);
            ps2.setString(3, context.getStrategyName());
            ps2.setInt(4, inventoryID);
            ps2.executeUpdate();
        }
    }

    public DiscountStrategy buildStrategy(String discountType, String percentText) {
        
        if (discountType.equals("Seasonal")) {
            return new SeasonalDiscount();
        }
        double percent = Double.parseDouble(percentText); 
        
        return new PercentageDiscount(percent);
    }

}
