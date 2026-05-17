package cpit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Singleton.DBconnectionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author aljaz
 */
public class ProductService {
    
    private final DBconnectionSingleton db =DBconnectionSingleton.getConnectionInstance();

    private final Connection con = db.getConnection();

    public ArrayList<String> getProductsByManager(int managerId) {

        ArrayList<String> items = new ArrayList<>();

        try {

            String sql = "SELECT InventoryID, Name FROM Inventory WHERE ManagerID = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, managerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("InventoryID");
                String name = rs.getString("Name");

                items.add(id + " - " + name);
            }

        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
        }

        return items;
    }    
    
    
    
    
    
    public void update(int inventoryID, String selectedField, Object newValue) {

        try {

            //query to update the value entered by the user
            String updateQuery = "UPDATE Inventory SET " + selectedField + " = ? WHERE InventoryID = ?";
            PreparedStatement ps = con.prepareStatement(updateQuery);
            
            ps.setObject(1, newValue);
            ps.setInt(2, inventoryID);
            
            ps.executeUpdate();

            System.out.println("Product updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //deletes the product asked by the user from the inventory 
    public void deleteProduct(int inventoryID) {

        try {
            String sql = "DELETE FROM Inventory WHERE InventoryID = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, inventoryID);
            ps.executeUpdate();

            System.out.println("Product deleted!");

        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
    
}