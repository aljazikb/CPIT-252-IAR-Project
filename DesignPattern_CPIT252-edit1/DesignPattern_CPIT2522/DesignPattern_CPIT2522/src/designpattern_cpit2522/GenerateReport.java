/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern_cpit2522;

import designpattern_cpit2522.DB_Singleton.DBconnectionSingleton;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aljaz
 */
public class GenerateReport {

    private final DBconnectionSingleton db=DBconnectionSingleton.getConnectionInstance();

    private final Connection con = db.getConnection();

    public String generateInventoryReport(int managerID) {

        StringBuilder report = new StringBuilder();

        try {

            String sql= "SELECT * FROM Inventory WHERE ManagerID = ?";

            PreparedStatement ps= con.prepareStatement(sql);

            ps.setInt(1, managerID);

            ResultSet rs=ps.executeQuery();

            // Header
            report.append("\t\t\t INVENTORY STOCK REPORT\n\n");

            report.append("Manager ID: ")
                    .append(managerID)
                    .append("\n\n");

            report.append(String.format("%-12s %-20s %-15s %-15s %-12s %-10s %-15s %-15s\n","ID","Name","Category","Type",
                    "Unit Price",
                    "Quantity",
                    "Total Value",
                    "Pricing"
            ));

            report.append("─".repeat(120))
                    .append("\n");

            // Data rows
            while (rs.next()) {

                int inventoryID= rs.getInt("InventoryID");
                String name= rs.getString("Name");
                String category= rs.getString("Category");
                String specificType= rs.getString("SpecificType");
                double unitPrice= rs.getDouble("UnitPrice");
                int quantity= rs.getInt("QuantityInStock");
                double totalValue= rs.getDouble("InventoryValue");
                String pricingStrategy= rs.getString("PricingStrategy");

                report.append(String.format("%-12d %-20s %-15s %-15s $%-11.2f %-10d $%-14.2f %-30s\n",inventoryID,name,category,specificType,
                        unitPrice,
                        quantity,
                        totalValue,
                        pricingStrategy
                ));
            }

            // Footer
            report.append("\n")
                    .append("=".repeat(120))
                    .append("\n");

            // Save file
            PrintWriter writer=new PrintWriter("Report.txt");
            writer.print(report.toString());
            writer.close();
            System.out.println("Report saved");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating report.";
        }

        return report.toString();
    }
}
